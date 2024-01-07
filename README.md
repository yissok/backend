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
- make api internal only
  - ~~add policy~~
  - verify with internal ec2 curl that it actually is accessible vpc
  - also test out if this works "Condition": { "StringNotEqualsIfExists": { "aws:username": "mario" } }

aws s3 cp src/main/resources/cf/mario.yaml s3://andreaciao/cf/
