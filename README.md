# Example Command Line Client for APH Adapter

## Build

This is very simple- you should be able to build this tool by making sure you Gradle installed (2.1+) and issuing the following command from the root directory:

```bash
gradle
```bash

Two jars will be in build/libs:

```bash
aph-client-example-capsule.jar	
aph-client-example.jar
```

You want to use the capsule; it has all dependencies inside the executable jar file.

To use, you just type

```bash
java -jar aph-client-example-capsule.jar
```

with the proper commands. The help output explains the commands:

```bash
java -jar aph-client-example-capsule.jar --help

Usage: <main class> [options]
  Options:
    --audience, -a
       The JWT audience string.
       Default: <removed for security>
    --clock-skew, -c
       The clock skew for an acceptable JWT token. Usually the default is fine.
       Default: 60
  * --endpoint, -e
       The endpoint we will be calling.
    --help
       Print this help message.
       Default: false
    --issuer, -i
       The JWT issuer string. This is usually hardcoded to the default value.
       Default: <removed for security>
  * --key, -k
       The JWT encryption key.
    --subject, -s
       The JWT subject string.
       Default: <removed for security>
    --url, -u
       The URL (including port) of the adapter.
       Default: <removed for security>
    -D
       The key/value parameters for the json body of the request.
       Syntax: -Dkey=value
       Default: {axisEvent=NewEvent, axisMember=TM111}
```

To call the generic endpoint, for instance, the following command (with the sensitve data ommitted for security) should be executed:

```bash
java -jar aph-client-example-capsule.jar -k <removed for security> -e /event/generic/<group id removed for security> -DaxisEvent=NewEvent -DaxisMember=<removed for security>
```

You will get back the following. It is a 'No Content' because I haven't added a bjond person id to map with an Axis id yet- I will need to do that in the db directly.

```bash
 ---------------------------------------------------------

JSON Payload: {"axisEvent":"NewEvent","axisMember":"<removed for security>"}
JWT Token: <removed for security>

 ---------------------------------------------------------

Endpoint: <removed for security>
Status: 204
Status Info: No Content
Response: null
```

