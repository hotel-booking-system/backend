## Hotel

- id
- nome do hotel
- localização do hotel
- email
- telefone
- website
- número de documento (CNPJ)
- tipo de hotel
- categoria do hotel
- classificação
- serviços disponíveis
- quantidade total de quartos
- tipos de quartos
- capacidade máxima do hotel
- acessibilidade
- descrição
- imagens do hotel
- política de cancelamento
- formas de pagamento
- idiomas falados pela equipe
- data do cadastro
- data do hotel (desde 19...)
- cídog de reserva
- código promicional (se houver)

### Política de cancelamento

- prazo de cancelamento
- multa de cancelamento (se houver)

### Categoria do Hotel

- Hotel
- Hostel
- Pousada
- Resourt
- Hotel Fazenda

### Formas de pagamento

- Dinheiro
- Cartão de débito
- Cartão de crédito
- PIX
- QR Code

### Pagamento no Cartão

- número do cartão
- data de vencimetno
- nome do titular do cartão
- código de segurança

### Moedas aceitas

- Real
- Dolár
- Euro

### Idiomas

- Português - Brasil
- Português - Portugal
- Inglês - Estados Unidos
- Inglês - Reino Unido
- Espanhol - Espanha
- Espanhol - América Latina
- Francês
- Italiano
- Alemão
- Japonês

## Quarto

- id
- número do quarto
- tipo de quarto
- capacidade
- valor/preço da diária
- quantidade de cama
- tamanho do quarto m²
- vista
- serviços disponíveis
- descrição do quarto
- imagens do quarto
- disponibilidade

### Tipos de quarto

- solteiro
- casal
- família
- presidencial

### Disponibilidade

- disponível
- reservado
- ocupado

### Serviços disponíveis

- ar condicionado
- café da manhã
- piscina
- piscina aquecida
- Spa

## Users

- id
- nome completo
- email
- senha
- número de telefone

# Customer

- id
- nome
- número de documento (CPF)
- endereço
- data do cadastro

## Hóspede

- id
- nome
- email
- número de telefone
- data de nascimento
- nacionalidade
- número de documento (CPF, RG, Passaporte)

## Reserva

- id (número randomico - iniciar com a letra 'R')
- data de chegada (check-in)
- data de saída (check-out)
- número de quartos
- tipo de quarto
- número de hóspedes adultos e crianças
- preferência de tipo de quarto: cama de casal, solteiro etc.
- preferência de andar (alto, baixo etc.)
- necessidades especiais (acessível para deficientes, quartos para fumantes etc.)
- nome do responsável pela reserva (se diferente do hóspede)
- adicionais
- garantia de reserva: efetuar depósito antecipado (se necessário)

### Adicionais

- Cama extra
- berço
- transfer

### Opções de reserva

- código de reserva
- efetuar reserva
- alterar reserva
- confirmação da reserva
- cancelar reserva

[ISO 4217 - CÓDIGOS DE MOEDA DO PAÍS](https://pt.iban.com/currency-codes)

---

## List<> vs Set<>

| #                 | `List<>`                                                                                                  | `Set<>`                                                                                  |
|:------------------|:----------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------|
| Ordem             | Preserva a ordem de inserção dos quartos.                                                                 | Não garante a ordem dos quartos.                                                         |
| Duplicatas        | Permite quartos duplicados na lista.                                                                      | Não permite quartos duplicados no conjunto.                                              |
| Acesso por índice | Permite acesso direto aos quartos por índice.                                                             | Não permite acesso direto aos quartos por índice.                                        |
| Performance       | Buscar um quarto por índice é rápido, mas adicionar ou remover um quarto no meio da lista pode ser lento. | Adicionar ou remover um quarto é rápido, mas buscar um quarto por índice pode ser lento. |

### Considerações

* Se você precisa preservar a ordem de inserção dos quartos e permitir duplicatas, use `List`.
* Se você não precisa da ordem de inserção dos quartos e não quer permitir duplicatas, use `Set`.
* Se você precisa acessar os quartos por índice, use `List`.
* Se você precisa de um desempenho rápido para adicionar ou remover quartos, use `Set`.

---

### Sobre o CNPJ

- Os 8 primeiros dígitos (XX.XXX.XXX) formam a 'raiz', que identifica a empresa
- Os 4 dígitos seguintes, após a barra (YYYY) foram o sufixo, que identifica uma unidade de atuação de empresa, ou seja,
  um endereço de ativiadde da pessoa jurídica
- Os 2 últimos dígitos, após o traço (ZZ) formam o 'dígito verificador', que é o resultdo de uma equação com os 12
  números anteriores.
- Deste modo, os CNPJs no formato XX.XXX.XXX/0001-ZZ identificam a matriz da empresa

---

* Dados da empresa: dados gerais de contato da empresa
    * nome
    * email
    * inscrição estadual
    * CNPJ
    * telefone
    * celular
    * data de abertura
* Endereço: endereço completo da sede da empresa
    * CEP
    * Logradouro
    * Número
    * Complemento
    * Bairro
    * Cidade
    * Estado
    * Sigla Estado

