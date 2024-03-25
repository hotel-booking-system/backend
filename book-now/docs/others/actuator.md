## Spring Actuator

O Spring Boot Actuator oferece vários endpoints para monitorar e gerenciar sua aplicação em produção. Vamos explicar a
função de cada um:

### Endpoints básicos

* `/actuator`: É o endpoint principal do Actuator. Geralmente ele retorna informações sobre os demais endpoints
  disponíveis.

### Endpoints de monitoramento

* `/actuator/beans`: Exibe uma lista completa de todos os beans do Spring gerenciados na sua aplicação.
* `/actuator/health`: Retorna informações sobre a saúde da sua aplicação, indicando se ela está funcionando
  corretamente.
* `/actuator/metrics`: Fornece métricas da aplicação, como uso de memória, CPU, timers e contadores personalizados.
* `/actuator/mappings`: Exibe detalhes sobre os mapeamentos de requisição configurados na sua aplicação, mostrando quais
  URLs estão associadas a quais métodos dos controladores.

### Endpoints de caches

* `/actuator/caches`: Mostra informações sobre todos os caches configurados na sua aplicação, incluindo seu tamanho e
  estado.
* `/actuator/caches/{cache}`: Permite consultar informações específicas de um cache individual, identificado pelo seu
  nome.

### Endpoints de informações

* `/actuator/info`: Retorna diversas informações gerais sobre a sua aplicação, como detalhes da build, dependências, Git
  properties e ambiente.
* `/actuator/conditions`: Exibe as condições de inicialização (EnvironmentConditions) utilizadas pelo Spring para
  iniciar sua aplicação.
* `/actuator/configprops` e `/actuator/configprops/{prefix}`: Fornece acesso às propriedades de configuração da sua
  aplicação. Você pode recuperar todas as propriedades ou filtrá-las por um prefixo específico.
* `/actuator/env` e `/actuator/env/{toMatch}`: Similar ao endpoint anterior, permite acessar as variáveis de ambiente
  configuradas para a sua aplicação. Você pode recuperar todas as variáveis ou filtrá-las por um padrão específico.

### Endpoints de gerenciamento

* `/actuator/loggers`: Permite alterar o nível de log de diferentes categorias na sua aplicação (pode exigir permissões
  de admin).
* `/actuator/heapdump`: Gera um dump da heap memory da sua aplicação em um arquivo (pode exigir permissões de admin).
* `/actuator/threaddump`: Cria um dump de todas as threads ativas na sua aplicação, incluindo seu estado (pode exigir
  permissões de admin).
* `/actuator/scheduledtasks`: Exibe informações sobre as tarefas agendadas configuradas na sua aplicação utilizando
  o `@Scheduled` annotation.

### Exemplos de Request e Response

* [Endpoints Request](./../../src/main/resources/static/request/actuator.http)
* [Response `/actuator`](./../../src/main/resources/static/response/actuator_response.json)