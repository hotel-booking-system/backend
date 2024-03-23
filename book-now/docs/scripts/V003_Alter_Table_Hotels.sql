-- Adiciona validações
ALTER TABLE hotels
    ADD CONSTRAINT hotel_name_min_length CHECK ( LENGTH(hotel_name) >= 3 ),
    ADD CONSTRAINT location_min_length CHECK (LENGTH(location) >= 10),
    ADD CONSTRAINT phone_number_format CHECK ( phone_number REGEXP '^\\+?[1-9]\\d{1,15}$' ),
    ADD CONSTRAINT email_format CHECK ( '^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$' ),
    ADD CONSTRAINT cnpj_number_format CHECK ( LENGTH(cnpj_number) = 14 ),
    ADD CONSTRAINT description_min_length CHECK ( LENGTH(description) >= 3 ),
    ADD CONSTRAINT room_count_min_value CHECK ( room_count >= 1 )
