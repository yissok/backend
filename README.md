# backend

Simple Java backend

./gradlew clean buildZip

## TODO

NEXT EPISODE: 
- ~~read through Creating a private API in Amazon API Gateway article again or find some tutorial~~
- ~~rebuild stack~~
- make sure node app starts automatically 
- ~~then do curl 'https://3g7ximgle3.execute-api.us-east-1.amazonaws.com/Prod/test//' from the ec2 host,~~
- ~~try to get it to not give you {"Message":"User: anonymous is not authorized to perform: execute-api:Invoke on resource: arn:aws:execute-api:us-east-1:********3748:3g7ximgle3/Prod/GET/test//"}~~
- ~~or https://repost.aws/knowledge-center/api-gateway-private-cross-account-vpce~~
- you got it working manually but cf template is still missing something, getting [ec2-user@ip-10-0-0-27 ~]$ curl https://qgay44hpqj.execute-api.us-east-1.amazonaws.com/Prod/ curl: (6) Could not resolve host: qgay44hpqj.execute-api.us-east-1.amazonaws.com from instance
- add depends on to set order in cf

...

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
