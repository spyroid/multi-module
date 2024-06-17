### mod-core:
- web
- db

### mod-solace:
- mod-core
- solace lib

### mod-mq:
- mod-core
- mq-lib

### mod-persist:
- mod-core
- validation
- persistence

### mod-lookup:
- mod-core
- mod-solace

app-solace:
- mod-core
- mod-solace
- mod-persist

app-mq:
- mod-core
- mod-mq
- mod-persist

app-files:
- mod-core
