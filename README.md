The application was tested with Faker format on Mockoon server:
```json
[
  {{#repeat 50}}
  {
    "id": "{{faker 'datatype.uuid'}}",
    "username": "{{faker 'internet.userName'}}",
    "avatar": "https://avatars.githubusercontent.com/u/{{faker 'datatype.number' 10000}}",
    "city": "{{faker 'address.city'}}",
    "country": "{{faker 'address.country'}}",
    "sex": "{{faker 'name.sexType'}}",
    "interestedIn": "{{faker 'name.sexType'}}",
    "description": "{{faker 'lorem.sentence'}}"
  }
  {{/repeat}}
]

```
