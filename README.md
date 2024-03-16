# backend

>>>>>>>Simple Java backend

# setup caveats beyond cloudformation

- choose elastic ip manually
- create vpc link separately (script for this is in gh actions)

# useful
- /var/log/cloud-init.log for user data log
- npm install -g forever && forever start server.js

# TODO

NEXT EPISODE:
- retrieve cert in userdata and put it in certs dir
  -  Unable to locate credentials. You can configure credentials by running "aws configure".


---

---

---

---

# TODO GRAVEYARD
- a
![IMG_B11D62A14D37-1](https://github.com/yissok/backend/assets/20245218/1b8479fc-6a80-46af-9924-a5717d46c95f)
<img width="715" alt="Screenshot 2024-03-16 at 08 49 34" src="https://github.com/yissok/backend/assets/20245218/5f4db144-3ee0-4ad5-b63c-15e3c8801c92">
- put cert in parameter store
- use domain yissok.com instead of public ip
  - create elastic ip to associate
- ssl
- upload jar s3
- ~~use cli~~
- ~~use githubaction~~
  - ~~install java~~
  - ~~install gradle~~
  - ~~package app~~
- store aws secret
  - ~~use secret to upload jar~~
  - use secret with CF https://stackoverflow.com/questions/62521811/how-to-pass-parameter-as-a-file-in-aws-cloudformation-deploy
- VPC stuff
  - create vpc, subnet and sec gr
  - also create igw and connect it to above vpc in rt table
  - fix cf to make api gateway live in newly created vpc
  - ok add to cloudformation vpc endpoint in that subnet and sec gr and also add that endpoint to the route table and add vpce endpoint to api gateway settings and allow inbound 443 in sec group
- deploy cf
  - ~~use cli~~
  - use githubaction
- make api internal only
  - ~~add policy~~
  - node app
    - verify with internal ec2 curl that it actually is accessible vpc
      - ~~create template node project~~
      - add custom proxy endpoint that performs http request as provided (not secure but just to test internal gateway)
      - destroy stack once verified
    - block requests without cookie
    - email signup
    - https://www.freecodecamp.org/news/deploy-nodejs-app-with-cloudformation/
  - also test out if this works "Condition": { "StringNotEqualsIfExists": { "aws:username": "mario" } }

aws s3 cp src/main/resources/cf/mario.yaml s3://andreaciao/cf/

- ~~read through Creating a private API in Amazon API Gateway article again or find some tutorial~~
- ~~rebuild stack~~
- make sure node app starts automatically
- ~~then do curl 'https://3g7ximgle3.execute-api.us-east-1.amazonaws.com/Prod/test//' from the ec2 host,~~
- ~~try to get it to not give you {"Message":"User: anonymous is not authorized to perform: execute-api:Invoke on resource: arn:aws:execute-api:us-east-1:********3748:3g7ximgle3/Prod/GET/test//"}~~
- ~~or https://repost.aws/knowledge-center/api-gateway-private-cross-account-vpce~~
- ~~you got it working manually but cf template is still missing something, getting [ec2-user@ip-10-0-0-27 ~]$ curl https://qgay44hpqj.execute-api.us-east-1.amazonaws.com/Prod/ curl: (6) Could not resolve host: qgay44hpqj.execute-api.us-east-1.amazonaws.com from instance~~
- ~~add depends on to set order in cf~~
- create stack cli
- continue execution 10 min after create stack
- java create pr to update s3 location of zip in cloudformation
- create update cf step on separate branch
- merge create and update steps i.e. if it does not exist, create, if not, update
- it was vpc endpoint... so just do it via cli in gh actions after cf is created
  - aws ec2 describe-vpcs --filters "Name=tag:Name,Values=MyVPC" --query "Vpcs[0].VpcId" --output text
  - aws ec2 describe-security-groups  --filters "Name=tag:Name,Values=MySecurityGroup" --query "SecurityGroups[0].GroupId" --output text
  - aws ec2 describe-subnets --filters "Name=tag:Name,Values=MySubnet" --query "Subnets[0].SubnetId" --output text
  - aws ec2 create-vpc-endpoint \
    --vpc-id vpc-0c4fa9bea900a8ca0 \
    --service-name com.amazonaws.us-east-1.execute-api \
    --subnet-ids subnet-042479f7b4b85aa66 \
    --security-group-ids sg-0c11430eeb2fdf23d \
    --vpc-endpoint-type Interface \
    --tag-specifications 'ResourceType=vpc-endpoint,Tags=[{Key=Name,Value=MyVpce}]'
  - aws apigateway get-rest-apis --query "items[?name=='YourApiGatewayName'].id" --output text
  - vpceId=$(aws ec2 describe-vpc-endpoints --query "VpcEndpoints[?Tags[?Key=='Name' && Value=='MyVpce']].VpcEndpointId" --output text)
  - aws apigateway update-rest-api \
    --rest-api-id mc5jjk7dl3 \
    --patch-operations "op='add',path='/endpointConfiguration/vpcEndpointIds',value='vpce-02dfcc4e2ec43b8f6'" \
    --region us-east-1
```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Deny",
      "Principal": "*",
      "Action": "execute-api:Invoke",
      "Resource": "arn:aws:execute-api:us-east-1:555690433748:k8jbqqvx4b/*/*/*",
      "Condition": {
        "StringNotEquals": {
          "aws:sourceVpc": "vpc-09d9d349e5f383138"
        }
      }
    },
    {
      "Effect": "Allow",
      "Principal": "*",
      "Action": "execute-api:Invoke",
      "Resource": "arn:aws:execute-api:us-east-1:555690433748:k8jbqqvx4b/*/*/*"
    }
  ]
}
```

---

---

---

---

# CERT STUFF

  ```bash
  CANAME=MyOrg-RootCA
  openssl genrsa -aes256 -out $CANAME.key 4096
  openssl req -x509 -new -nodes -key $CANAME.key -sha256 -days 1826 -out $CANAME.crt
  ```

- install by double clicking res file

  ```bash
  MYCERT=MyServer
  openssl req -new -nodes -out $MYCERT.csr -newkey rsa:4096 -keyout $MYCERT.key
  touch $MYCERT.v3.ext
  ```
- paste this

  ```
  authorityKeyIdentifier=keyid,issuer
  basicConstraints=CA:FALSE
  keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
  subjectAltName = @alt_names
  
  [alt_names]
  DNS.1 = localhost
  ```
- sign

  ```bash
  openssl x509 -req -in $MYCERT.csr -CA $CANAME.crt -CAkey $CANAME.key -CAcreateserial -out $MYCERT.crt -days 730 -sha256 -extfile $MYCERT.v3.ext
  ```
- TEARDOWN - delete vpc endpoint > network interface > vpc
