# backend

Simple Java backend


## TODO


- store secret deploy from local with cli and get secret with https://stackoverflow.com/questions/62521811/how-to-pass-parameter-as-a-file-in-aws-cloudformation-deploy in cloudformation
- package jar and upload with cli
- github actions packages jar and uploads to s3
- use githubaction to deploy cf
- add below to cf to make api internal only
    ```json
    {
        "Version": "2012-10-17",
        "Statement": [
            {
                "Effect": "Allow",
                "Principal": {
                    "AWS": [
                        "arn:aws:iam::555690433748:user/mario"
                    ]
                },
                "Action": "execute-api:Invoke",
                "Resource": "*"
            }
        ]
    }
    ```