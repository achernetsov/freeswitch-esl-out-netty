# Overview
Example of spring boot application with netty for transport, 
interacting with Freeswitch using [ESL outbound](https://freeswitch.org/confluence/display/FREESWITCH/Event+Socket+Outbound)

See [Netcat Example](https://freeswitch.org/confluence/display/FREESWITCH/Event+Socket+Outbound#EventSocketOutbound-UsingNetcat); 
Use same diaplan to connect FS to this application.

# Dependencies
Spring used for Spring Boot and DI

Netty used for transport

# Instruction
1. Create dialplan extension, like so:
```$xslt
    <extension name="callToSpringApp">
        <condition field="destination_number" expression="74996">
            <action application="log" data="test outbound socket"/>
            <action application="socket" data="127.0.0.1:8084 async full"/>
        </condition>
    </extension>
```
2. Run application this Java application

3. Perform call on 74996; You will see answer on connect command in console output

