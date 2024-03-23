## Triggers

Triggers, ou gatilhos, são procedimentos SQL que são executados automaticamente em resposta a eventos específicos em uma
tabela. Eles podem ser usados para realizar diversas tarefas, como:

- Validar dados
- Atualizar outras tabelas
- Enviar notificações
- Implementar regras de negócio

### Criando Triggers

A sintaxe para criar um trigger varia um pouco entre os diferentes sistemas de gerenciamento de banco de dados (SGBDs),
mas geralmente inclui os seguintes elementos:

- **Nome do Trigger:** Um nome único que identifica o trigger.
- **Evento:** O evento que acionará o trigger. Os eventos mais comuns são INSERT, UPDATE e DELETE.
- **Tabela:** A tabela na qual o evento ocorrerá.
- **Ação:** O código SQL que será executado quando o trigger for acionado.

---

## Implementando Triggers no Banco de Dados booknow_db (MySQL)

**1. Trigger para Validar a Capacidade do Quarto:**

**Objetivo:** Garantir que a capacidade do quarto não seja excedida ao fazer uma reserva.

**Trigger:**

```sql
CREATE TRIGGER before_insert_reservation
ON reservations
FOR INSERT
BEGIN
  DECLARE available_rooms INT;

SELECT room_count - COUNT(*) INTO available_rooms
FROM reservations
WHERE room_id = NEW.room_id AND start_date BETWEEN NEW.start_date AND NEW.end_date;

IF available_rooms <= 0 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Quarto indisponível para as datas selecionadas.';
END IF;
END;
```

**Explicação:**

* O trigger é executado antes de inserir uma nova linha na tabela `reservations`.
* Ele calcula o número de quartos disponíveis para o quarto especificado e as datas da reserva.
* Se o número de quartos disponíveis for menor ou igual a 0, o trigger gera um erro e impede a inserção.

**2. Trigger para Atualizar o Status do Quarto:**

**Objetivo:** Atualizar o status do quarto para "ocupado" quando uma reserva é feita e para "disponível" quando a
reserva termina.

**Trigger:**

```sql
CREATE TRIGGER after_insert_reservation
ON reservations
FOR INSERT
BEGIN
  UPDATE bedrooms
  SET available = FALSE
  WHERE id = NEW.room_id;
END;

CREATE TRIGGER after_delete_reservation
ON reservations
FOR DELETE
BEGIN
  UPDATE bedrooms
  SET available = TRUE
  WHERE id = OLD.room_id;
END;
```

**Explicação:**

* O primeiro trigger é executado após inserir uma nova linha na tabela `reservations`.
* Ele atualiza o status do quarto para "ocupado" na tabela `bedrooms`.
* O segundo trigger é executado após excluir uma linha da tabela `reservations`.
* Ele atualiza o status do quarto para "disponível" na tabela `bedrooms`.

**3. Trigger para Log de Auditoria:**

**Objetivo:** Registrar todas as alterações feitas nas tabelas `hotels` e `bedrooms`.

**Trigger:**

```sql
CREATE TRIGGER audit_hotel_change
ON hotels
FOR INSERT, UPDATE, DELETE
BEGIN
  INSERT INTO audit_log (table_name, operation, timestamp, user)
  VALUES ('hotels', NEW.operation, CURRENT_TIMESTAMP, CURRENT_USER);
END;

CREATE TRIGGER audit_bedroom_change
ON bedrooms
FOR INSERT, UPDATE, DELETE
BEGIN
  INSERT INTO audit_log (table_name, operation, timestamp, user)
  VALUES ('bedrooms', NEW.operation, CURRENT_TIMESTAMP, CURRENT_USER);
END;
```

**Explicação:**

* Os triggers são executados após qualquer alteração nas tabelas `hotels` e `bedrooms`.
* Eles registram a operação realizada, a data e hora da alteração e o usuário que a fez na tabela `audit_log`.

---

## Triggers para o banco de dados booknow_db em MySQL

**1. Trigger para garantir que a quantidade de quartos disponíveis seja sempre positiva:**

```sql
CREATE TRIGGER before_update_bedroom
ON bedrooms
FOR UPDATE
BEGIN
  IF NEW.available = FALSE AND NEW.capacity - NEW.reservations < 1 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Não é possível desabilitar um quarto com reservas.';
END IF;
END;
```

Este trigger verifica se a quantidade de quartos disponíveis seria negativa após a atualização. Se for, ele gera um erro
e impede a atualização.

**2. Trigger para atualizar a data de atualização do hotel:**

```sql
CREATE TRIGGER before_update_hotel
ON hotels
FOR UPDATE
BEGIN
  SET NEW.updated_at = CURRENT_TIMESTAMP;
END;
```

Este trigger garante que a data de atualização do hotel seja sempre atualizada quando qualquer informação do hotel for
modificada.

**3. Trigger para enviar um email quando um novo quarto for criado:**

```sql
CREATE TRIGGER after_insert_bedroom
ON bedrooms
FOR INSERT
BEGIN
  DECLARE email VARCHAR(255);

SELECT email
FROM hotels
WHERE id = NEW.hotel_id;

IF email IS NOT NULL THEN
    SEND EMAIL TO email
    SUBJECT 'Novo quarto disponível!'
    MESSAGE 'Um novo quarto foi criado no hotel: ' || NEW.number;
END IF;
END;
```

Este trigger envia um email para o administrador do hotel quando um novo quarto é criado.

**4. Trigger para verificar se o hotel existe antes de criar um quarto:**

```sql
CREATE TRIGGER before_insert_bedroom
ON bedrooms
FOR INSERT
BEGIN
  DECLARE hotel_exists BOOLEAN;

SELECT EXISTS(SELECT id FROM hotels WHERE id = NEW.hotel_id) INTO hotel_exists;

IF NOT hotel_exists THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'O hotel especificado não existe.';
END IF;
END;
```

Este trigger garante que o hotel especificado no novo quarto exista antes de criar o quarto.

