# backend

Simple Java backend


## TODO

- get db secret from aws
- convert sam to cf
- insert
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