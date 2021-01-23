## Providers

| Name | Version |
|------|---------|
| aws | ~> 3.0 |
| local | ~> 2.0.0 |
| tls | ~> 3.0.0 |

## Inputs

| Name | Description | Type | Default | Required |
|------|-------------|------|---------|:-----:|
| app\_blue\_s3\_archive | Blue WAR file s3 URL | `any` | n/a | yes |
| app\_green\_s3\_archive | Green WAR file s3 address | `any` | n/a | yes |
| app\_name | Application name | `string` | `"funfact"` | no |
| infra\_region | Region where infrastructure will be created | `string` | `"us-east-1"` | no |
| instance\_count | Number of instances serving the traffic | `number` | `4` | no |
| tomcat\_version | Tomcat version to install on the machine | `string` | `"9.0.41"` | no |
| traffic\_distribution | Levels of traffic distribution | `string` | n/a | yes |

## Outputs

| Name | Description |
|------|-------------|
| lb\_dns | n/a |

