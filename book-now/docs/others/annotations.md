### Anotações do Bean Validation

- `@AssertFalse`: O valor do campo ou propriedade deve ser falso. Exemplo: `@AssertFalse boolean isUnsupported;`
- `@AssertTrue`: O valor do campo ou propriedade deve ser verdadeiro. Exemplo: `@AssertTrue boolean isActive;`
- `@DecimalMax`: O valor do campo ou propriedade deve ser um valor decimal menor do que ou igual ao número no elemento
  de valor. Exemplo: `@DecimalMax BigDecimal desconto;`
- `@DecimalMin`: O valor do campo ou propriedade deve ser um número dentro de um intervalo especificado.
  Exemplo: `@DecimalMin BigDecimal desconto;`
- `@Digits`: O valor do campo ou propriedade deve ser um número dentro de um intervalo especificado. O elemento inteiro
  especifica o máximo de dígitos integrais para o número, eo elemento fração especifica o máximo de dígitos fracionários
  para o número. Exemplo: `@Digits(integer = 6 fração, = 2) BigDecimal preço;`
- `@Future`: O valor do campo ou propriedade deve ser uma data no futuro. Exemplo: `@Future Data eventData`
- `@Max`: O valor do campo ou propriedade deve ser um valor inteiro menor do que ou igual ao número no elemento de
  valor. Exemplo: `@Max(10) int quantidade;`
- `@Min`: O valor do campo ou propriedade deve ser um valor inteiro maior que ou igual ao número no elemento de valor.
  Exemplo: `@Min(5) int quantidade;`
- `@NotNull`: O valor do campo ou da propriedade não deve ser nula. Exemplo: `@NotNull String nome`;
- `@Null`: O valor do campo ou propriedade deve ser nula. Exemplo: `@Null String nome`;
- `@Past`: O valor do campo ou propriedade deve ser uma data no passado. Exemplo: `@Past Date aniversario;`
- `@Pattern`: O valor do campo ou propriedade deve corresponder a expressão regular definida no elemento regexp.
  Exemplo: `@ Pattern(regexp = "\\(\\d{3}\\)\\d{3}-\\d{4}") String telefone;`;
- `@Size`: O tamanho do campo ou propriedade é avaliado e devem coincidir com os limites especificados. Se o campo ou
  propriedade é uma String, o tamanho da string é avaliado. Se o campo ou propriedade é uma coleção, o tamanho da
  coleção é avaliado. Se o campo ou propriedade é um mapa, o tamanho do Mapa é avaliado. Se o campo ou propriedade é uma
  matriz, o tamanho da matriz é avaliado. O uso dos dois elementos, max e min, para especificar os limites é opcional,
  sendo obrigatório o uso de apenas um deles. Exemplo: `@Size(min = 2, max = 240) String nome`;
- `@NotNull`: O valor do campo ou da propriedade não deve ser nula. Exemplo: `@NotNull String nome`;

### Anotações de Camadas

- `@Controller` ou `@RestController`: Usado para definir o Controller da nossa aplicação.
- `@Service`: Usado para definir que uma classe é um serviço (camada de serviço).
- `@Repository`: Usado para definir que uma classe é um repositório (camada de persistência).
- `@Configuration`: Usado para definir que uma classe é uma classe de configuração do Spring.
- `@ControllerAdvice` ou `@RestControllerAdvice`: Usado para definir que uma classe será responsável por centralizar o
  tratamento de exceções.
- `@Component`: Usado para definir que uma classe é um componente gerenciado pelo contêiner de inversão de controle (IoC
  container).
- `@Bean`: Usado para definir que um método será responsável por (produzir/fornecer) um objeto gerenciado pelo contêiner
  de inversão de controle do Spring.

### Anotações de um Controller

- `@Controller`: Usado para definir o controller, no caso em um aplicativo Spring MVC.
- `@RestController`: Usado para definir o controller de uma api.
- `@RequestMapping("/api")`: Usado para mapear a url de um recurso da api.

### Anotações dos métodos do Controller de uma API

- `@GetMapping`: Anotação usado no método de requisição da api (Get).
- `@PutMapping(value = “/{id}”)`: Anotação usado no método de requisição da api (Put).
- `@PostMapping`: Anotação usado no método de requisição da api (Post).
- `@DeleteMapping(“/{id}”)`: Anotação usado no método de requisição da api (Delete).

### Outras anotações

- `@RequestParam(defaultValue = “0”) int page`: Usado para mapear o parâmetro de uma rota da api.
- `@ResponseStatus(code = HttpStatus.CREATED)`: Usado para definir a resposta da rota de uma api, no exemplo retorna o
  status (criado=201).
- `@PathVariable Long id`: Usado para mapear a variável da rota de uma api, no caso abaixo mostra como pegar a variável
  id da rota get. Exemplo:

```
@GetMapping(“/{id}”)
public ResponseDTO findById(@PathVariable Long id) {
    return service.findById(id);
}
```

- `@RequestBody RequestDTO requestDTO`: Usado para obter o corpo(body) de uma requisição da api. Exemplo:

```
@PostMapping
public ResponseDTO create(@RequestBody RequestBodyDTO requestBodyDTO) {
    return service.create(requestBodyDTO);
}
```

### Anotações de validação

- `@Validated`: Usado para indicar que a validação de um grupo de elementos a serem validados, permiti a validação
  parcial de um objeto.
- `@Valid`: Usado validar um item ou objeto.

### Anotações de persistência do JPA , ORM

- `@Entity`: Usado para indicar que a classe é uma entidade JPA.
- `@Table(name = “nome_tabela”)`: Usado para definir o nome da tabela.
- `@Id`: Informa que o campo é uma chave primária da entidade.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Informa que o ID deve ser gerado automaticamente.
- `@Id`: Informa que o campo é uma chave primária da entidade.
- `@Column(name=’nome da coluna’.length = 11, nullable = false)`: Mapeia uma coluna da entidade, especificando o nome, o
  comprimento e não informa que não permite nulo.
- `@ManyToOne(fetch = FetchType.LAZY, optional = false)`: Define um relacionamento (muitos para um), especificando o
  carregamento lazy e um relacionamento não opcional. Exemplos de `strategy`:
    - GenerationType.AUTO
    - GenerationType.IDENTITY
    - GenerationType.SEQUENCE
- `@OneToMany(mappedBy = “movimentacaoCEI”, cascade = CascadeType.ALL)`: Define um relacionamento (um para muitos),
  especificando o atributo relacionado na outra classe e que qualquer ação nesta entidade terá um efeito cascata na
  outra, exemplo um delete.
- `@ManyToMany`: Define um relacionamento (muitos para muitos).
- `@OrderBy("id ASC")`: Usado para especificar a ordenação dos elementos de uma consulta.
- `@JoinColumn(name = “entidade_id”, nullable = false)`: Define a coluna de chave estrangeira usada para o
  relacionamento informando que essa colina não pode receber valor nulo.

### Outras Anotações

- `@Autowired`: Usado para injetar dependências em métodos, ou seja inicializar.
- `@JsonInclude(Include.NON_NULL)`: Informa que apenas as propriedades que não são nulas serão exibidas no JSON.
- `@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)`: Informa que a propriedade anotada deve apenas ser
  serializada e não desserializada, usado para evitar que alguns campos não sejam expostos nas respostas da API.
- `@JsonProperty("_id") Long id`: Usado para renomear uma propriedade, no caso como a propriedade será exibida no json.
- `@ExceptionHandler({NotFoundException.class, RecordNotFoundException.class})`: Usado para capturar e tratar exceções
  específicas em métodos de um controlador de tratamento de exceções, no exemplo ele captura exceções `NotFound`
  e `RecordNotFound`. Exemplo:

```
@ExceptionHandler({NotFoundException.class, RecordNotFoundException.class})
public ResponseEntity<String> handleNotFoundException(Exception ex) {
    return new ResponseEntity<>(“Recurso não encontrado: ” + ex.getMessage(), HttpStatus.NOT_FOUND);
}
```

- `@Value`: Usada para injetar valores de propriedades em campos de uma classe que é gerenciada pelo Spring, no exemplo
  pego as informação do arquivo (application.properties) e injeto no atributo.

    - Valor no arquivo (application.properties)

  ```
  path.folder.files=/caminho/dos-arquivos
  ```

    - Mapeamento no atributo da classe

  ```
  @Component
  public class Classe {
      @Value(“${path.folder.files}”)
      private String pathFolder;
  }
  ```