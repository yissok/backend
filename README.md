# backend

Simple Java backend


## TODO



- upload jar s3
  - ~~use cli~~
  - ~~use githubaction~~
    - ~~install java~~
    - ~~install gradle~~
    - ~~package app~~
- store aws secret
  - ~~use secret to upload jar~~
  - use secret with CF https://stackoverflow.com/questions/62521811/how-to-pass-parameter-as-a-file-in-aws-cloudformation-deploy
- deploy cf
  - ~~use cli~~
  - use githubaction
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