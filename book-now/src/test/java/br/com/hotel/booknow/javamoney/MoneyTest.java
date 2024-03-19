package br.com.hotel.booknow.bedrooms;

import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.UnknownCurrencyException;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

import static javax.money.Monetary.getCurrency;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JSR 354 -  aborda a padronização de moedas e valores monetários em Java.
 * <p>
 * Seu objetivo é adicionar uma API flexível e extensível ao ecossistema Java e tornar o trabalho com valores monetários
 * mais simples e seguro.
 * <p>
 * <b>Recursos</b>
 * <p>
 * Os objetivos da API “Moeda e Dinheiro”:
 * <ul>
 *     <li>Fornecer uma API para manipulação e cálculo de valores monetários</li>
 *     <li>Definir classes representativas de moedas e valores monetários, bem como arredondamentos monetários</li>
 *     <li>Para lidar com taxas de câmbio</li>
 *     <li>Para lidar com a formatação e análise de moedas e valores monetários</li>
 * </ul>
 */
@Slf4j
class MoneyTest {

	/**
	 * <b>1. CurrencyUnit (Unidade Monetária)</b>: Modela as propriedades mínimas de uma moeda. Suas instâncias podem
	 * ser obtidas usando o método <code>Monetary.getCurrency()</code>
	 * <p>
	 * Ver lista de Códigos de Moeda do País: ISO-4217
	 *
	 * <ul><b>Estados Unidos da América</b>
	 * 		<li>Currency: Dólar americano</li>
	 * 		<li>Currency Code: USD</li>
	 * 	    <li>Numeric Code: 840</li>
	 * </ul>
	 * <ul><b>Brasil</b>
	 * 		<li>Currency: Real brasileiro</li>
	 * 		<li>Currency Code: BRL</li>
	 * 	    <li>Numeric Code: 986</li>
	 * </ul>
	 */
	@Test
	@DisplayName(value = "Obter CurrencyUnit a partir de código de moeda existente")
	void givenCurrencyCode_whenString_thanExist() {

		log.info("Iniciando teste: Obter CurrencyUnit com código BRL");

		String CURRENCY_CODE = "BRL";
		int NUMERIC_CODE = 986;
		int DEFAULT_FRACTION_DIGITS = 2;

		CurrencyUnit brl = Monetary.getCurrency(CURRENCY_CODE);

		assertNotNull(brl);
		assertEquals(brl.getCurrencyCode(), CURRENCY_CODE);
		assertEquals(brl.getNumericCode(), NUMERIC_CODE);
		assertEquals(brl.getDefaultFractionDigits(), DEFAULT_FRACTION_DIGITS);

		log.info("Teste finalizado com sucesso!");

	}

	/**
	 * 1.1 Criamos <code>CurrencyUnit</code> usando uma representação String da moeda, isso pode levar a uma situação em
	 * que tentamos criar uma moeda com código inexistente. A criação de moedas com códigos inexistentes gera uma
	 * exceção <code>UnknownCurrency</code>
	 */
//	@Test
	@DisplayName(value = "Tentar obter CurrencyUnit a partir de código de moeda inexistente gera exceção")
	void givenCurrencyCode_whenNoExist_thanThrowsError() throws UnknownCurrencyException {
		log.info("Iniciando teste: Obter CurrencyUnit com código inexistente (AAA)");

		String CURRENCY_CODE = "AAA";

		try {
			// Teste falhou intencionanlmente, pois tenta obter uma unidade monetária com código inexistente
			// Funciona como o esperado para verficiar o comportamento em casos de erro.
			Monetary.getCurrency(CURRENCY_CODE);
		} catch (UnknownCurrencyException e) {
			log.error("Moeda com código 'AAA' não existe!", e);
			throw e;
		}
	}

	/**
	 * <b>2. MonetaryAmount (Valor monetário)</b>: é uma representação numérica de um valor monetário. Está sempre
	 * associado a <code>CurrencyUnit<code> e define uma representação monetária de uma moeda.
	 * <p>
	 * O valor pode ser implementado de diferentes formas, focando no comportamento dos requisitos de representação
	 * monetária, definidos por cada caso de uso concreto. Por exemplo. Money e FastMoney são implementações da
	 * interface <code>MonetaryAmount</code>.
	 * <p>
	 * FastMoney implementa <code>MonetaryAmount</code> usando representação numérica longa e é mais rápido que
	 * <code>BigDecimal</code> ao custo da precisão; ele pode ser usado quando precisamos de desempenho e precisão não
	 * é um problema.
	 * <p>
	 * Uma instância genérica pode ser criada usando uma fábrica padrão. Vamos mostrar as diferentes maneiras de obter
	 * instâncias de <code>MonetaryAmount</code>
	 */
	@Test
	@DisplayName(value = "Verificar conversão de valores monetários para String")
	void givenAmounts_whenStringified_thanEquals() {

		CurrencyUnit usd = getCurrency("USD");

		MonetaryAmount fstAmtUSD = Monetary.getDefaultAmountFactory()
				.setCurrency(usd).setNumber(200).create();

		Money moneyof = Money.of(12, usd);

		FastMoney fastmoneyof = FastMoney.of(2, usd);

		log.info("Verificando conversão de CurrencyUnit para String: {}", usd.toString());
		assertEquals("USD", usd.toString());

		log.info("Verificando conversão de MonetaryAmount para String: {}", fstAmtUSD.toString());
		assertEquals("USD 200", fstAmtUSD.toString());

		log.info("Verificando conversão de Money para String: {}", moneyof.toString());
		assertEquals("USD 12", moneyof.toString());

		log.info("Verificando conversão de FastMoney para String: {}", fastmoneyof.toString());
		assertEquals("USD 2.00000", fastmoneyof.toString());

	}

	/**
	 * <b>3. Monetary Arithmetic (Aritmética Monetária)</b>
	 * <p>
	 * Podemos realizar aritmética monetária entre Money e FastMoney , mas precisamos ter cuidado ao combinar instâncias
	 * dessas duas classes.
	 * <p>
	 * Por exemplo, quando comparamos uma instância Euro do FastMoney com uma instância Euro do Money , o resultado é
	 * que elas não são iguais:
	 */
	@Test
	@DisplayName(value = "Comparação entre moedas de diferentes unidades monetárias")
	void givenCurrencies_whenCompared_thanNotequal() {

		MonetaryAmount oneDolar = Monetary.getDefaultAmountFactory()
				.setCurrency("USD").setNumber(1).create();

		Money oneEuro = Money.of(1, "EUR");

		log.info("Comparando Money (USD) com FastMoney (EUR):");
		assertNotEquals(oneEuro, FastMoney.of(1, "EUR"));

		log.info("Comparando Money (USD) com Money (USD):");
		assertEquals(oneDolar, Money.of(1, "USD"));

	}

	/**
	 * Podemos realizar adição, subtração, multiplicação, divisão e outras operações aritméticas monetárias usando os
	 * métodos fornecidos pela classe MonetaryAmount .
	 * <p>
	 * As operações aritméticas devem lançar uma ArithmeticException , se as operações aritméticas entre valores
	 * superarem as capacidades do tipo de representação numérica usada, por exemplo, se tentarmos dividir um por três,
	 * obteremos uma ArithmeticException porque o resultado é um número infinito (método abaixo).
	 * <p>
	 * Ao somar ou subtrair valores, é melhor utilizar parâmetros que sejam instâncias de MonetaryAmount , pois
	 * precisamos garantir que ambos os valores tenham a mesma moeda para realizar operações entre valores.
	 */
//	@Test
	@DisplayName(value = "Dividir MonetaryAmount por zero deve gerar exceção")
	void givenAmount_whenDivided_thanThrowsException() throws ArithmeticException {

		CurrencyUnit usd = getCurrency("USD");

		try {
			MonetaryAmount oneDolar = Monetary.getDefaultAmountFactory()
					.setCurrency(usd).setNumber(1).create();
			log.info("Tentando dividir MonetaryAmount (USD 1.0) por 3");
			// Teste falhou intencionalmente, pois tenta dividir um MonetaryAmount por zero
			// Funciona como o esperado para verficiar o comportamento em casos de erro.
			oneDolar.divide(3);
		} catch (ArithmeticException e) {
			log.error("Exceção capturada: Divisão por zero", e);
			throw e;
		}

	}

	/**
	 * <b>3.1 Calculating Amounts (Cálculo de Valores)</b>
	 * <p>
	 * Um total de valores pode ser calculado de várias maneiras, uma maneira é simplesmente encadear os valores como
	 * apresentado no método abaixo.
	 * <p>
	 * O encadeamento também pode ser aplicado à subtração: <code>Money calcAmtUSD = Money.of(1,
	 * "USD").subtract(fstAmtUSD);</code>
	 * <p>
	 * Multiplicando: <code>MonetaryAmount multiplyAmount = oneDolar.multiply(0.25);</code>
	 * <p>
	 * Ou dividindo: <code>MonetaryAmount divideAmount = oneDolar.divide(0.25);</code>
	 */
	@Test
	@DisplayName(value = "Somar MonetaryAmounts com mesma moeda deve resultar no valor correto")
	void givenAmounts_whenSummed_thanCorrect() {

		var monetaryAmounts = new MonetaryAmount[]{
				Money.of(100, "CHF"),
				Money.of(10.20, "CHF"),
				Money.of(1.15, "CHF")};

		Money sumAmtCHF = Money.of(0, "CHF");

		log.info("Somando os seguintes valores:");
		for (MonetaryAmount monetaryAmount : monetaryAmounts) {
			log.info("- {}", monetaryAmount.toString());
			sumAmtCHF = sumAmtCHF.add(monetaryAmount);
		}

		assertEquals("CHF 111.35", sumAmtCHF.toString());

	}

	/**
	 * Vamos comparar nossos resultados aritméticos usando Strings, visto que com Strings porque o resultado também
	 * contém a moeda:
	 */
	@Test
	@DisplayName(value = "Verificar conversão de operações aritméticas para String")
	void givenArithmetic_whenStringified_thanEqualsAmount() {

		CurrencyUnit usd = getCurrency("USD");

		Money moneyof = Money.of(12, usd);

		MonetaryAmount fstAmtUSD = Monetary.getDefaultAmountFactory()
				.setCurrency(usd).setNumber(200.50).create();
		MonetaryAmount oneDolar = Monetary.getDefaultAmountFactory()
				.setCurrency("USD").setNumber(1).create();

		Money subtractedAmount = Money.of(1, "USD").subtract(fstAmtUSD);
		MonetaryAmount multiplyAmount = oneDolar.multiply(0.25);
		MonetaryAmount divideAmount = oneDolar.divide(0.25);

		log.info("Verificando conversão de CurrencyUnit para String: {}", usd.toString());
		assertEquals("USD", usd.toString());

		log.info("Verificando conversão de MonetaryAmount para String: {}", oneDolar.toString());
		assertEquals("USD 1", oneDolar.toString());

		log.info("Verificando conversão de MonetaryAmount para String (valor inicial): {}", fstAmtUSD.toString());
		assertEquals("USD 200.5", fstAmtUSD.toString());

		log.info("Verificando conversão de Money para String: {}", moneyof.toString());
		assertEquals("USD 12", moneyof.toString());

		log.info("Verificando conversão de Money para String (subtração): {}", subtractedAmount.toString());
		assertEquals("USD -199.5", subtractedAmount.toString());

		log.info("Verificando conversão de MonetaryAmount para String (multiplicação): {}", multiplyAmount.toString());
		assertEquals("USD 0.25", multiplyAmount.toString());

		log.info("Verificando conversão de MonetaryAmount para String (divisão): {}", divideAmount.toString());
		assertEquals("USD 4", divideAmount.toString());

	}

	/**
	 * <b>4. Monetary Rounding (Arredondamento Monetário)</b>
	 * <p>
	 * O arredondamento monetário nada mais é do que a conversão de um valor com precisão indeterminada para um valor
	 * arredondado.
	 * <p>
	 * Usaremos a API getDefaultRounding fornecida pela classe Monetary para fazer a conversão. Os valores de
	 * arredondamento padrão são fornecidos pela moeda:
	 */
	@Test
	@DisplayName(value = "Arredondamento de MonetaryAmount")
	void givenAmount_whenRounded_thanEquals() {

		MonetaryAmount fstAmtEUR = Monetary.getDefaultAmountFactory()
				.setCurrency("EUR").setNumber(1.30473908).create();
		log.info("Valor inicial (EUR): {}", fstAmtEUR.toString());

		MonetaryAmount roundEUR = fstAmtEUR.with(Monetary.getDefaultRounding());
		log.info("Valor arredondado (EUR): {}", roundEUR.toString());

		assertEquals("EUR 1.30473908", fstAmtEUR.toString());
		assertEquals("EUR 1.3", roundEUR.toString());

	}

	/**
	 * <b>5. Currency Conversion (Conversão de moeda)</b>
	 * <p>
	 * A conversão de moeda é um aspecto importante ao lidar com dinheiro. Infelizmente, essas conversões têm uma grande
	 * variedade de implementações e casos de uso diferentes.
	 * <p>
	 * A API concentra-se nos aspectos comuns da conversão de moeda com base na moeda de origem, na moeda de destino e
	 * na taxa de câmbio.
	 * <p>
	 * A conversão de moeda ou o acesso às taxas de câmbio podem ser parametrizados, como no exemplo abaixo.
	 * <p>
	 * Uma conversão está sempre vinculada à moeda. MonetaryAmount pode ser simplesmente convertido passando um
	 * CurrencyConversion para o método with do valor .
	 */
//	@Test
	@DisplayName(value = "Conversão de MonetaryAmount para outra moeda")
	void givenAmount_whenConversion_thenNotNull() {

		MonetaryAmount oneDollar = Monetary.getDefaultAmountFactory().setCurrency("USD")
				.setNumber(1).create();
		log.info("Valor inicial (USD): {}", oneDollar.toString());

		// Teste falhou porque a conversão de moeda não está configurada
		CurrencyConversion conversionEUR = MonetaryConversions.getConversion("EUR");
		log.info("Tentando converter USD para EUR");

		MonetaryAmount convertedAmountUSDtoEUR = oneDollar.with(conversionEUR); // ERROR - TESTE FALHOU

		assertEquals("USD 1", oneDollar.toString());
		assertNotNull(convertedAmountUSDtoEUR);

	}

	/**
	 * <b>6. Currency Formatting (Formatação de moeda)</b>
	 * <p>
	 * A formatação permite o acesso de formatos baseados em java.util.Locale . Ao contrário do JDK, os formatadores
	 * definidos por esta API são thread-safe:
	 */
	@Test
	@DisplayName(value = "Formatação de MonetaryAmount de acordo com a localidade")
	void givenLocale_whenFormatted_thanEquals() {

		MonetaryAmount oneDollar = Monetary.getDefaultAmountFactory()
				.setCurrency("USD").setNumber(1).create();
		log.info("Valor inicial (USD): {}", oneDollar.toString());

		MonetaryAmountFormat formatUSD = MonetaryFormats.getAmountFormat(Locale.US);
		String usFormatted = formatUSD.format(oneDollar);

		assertEquals("USD 1", oneDollar.toString());
		assertNotNull(formatUSD);
		assertEquals("USD1.00", usFormatted);

	}

	/**
	 * Aqui estamos usando o formato predefinido e criando um formato personalizado para nossas moedas. O uso do formato
	 * padrão é simples usando o formato do método da classe MonetaryFormats . Definimos nosso formato personalizado
	 * definindo a propriedade padrão do construtor de consulta de formato.
	 * <p>
	 * Como antes, como a moeda está incluída no resultado, testamos nossos resultados usando Strings:
	 */
	@Test
	@DisplayName(value = "Formatação customizada de MonetaryAmount")
	void givenAmount_whenCustomFormat_thanEquals() {

		MonetaryAmount oneDollar = Monetary.getDefaultAmountFactory()
				.setCurrency("USD").setNumber(1).create();
		log.info("Valor inicial (USD): {}", oneDollar.toString());

		MonetaryAmountFormat customFormat = MonetaryFormats.getAmountFormat(
				AmountFormatQueryBuilder.of(Locale.US)
						.set(CurrencyStyle.NAME)
						.set("pattern", "00000.00 ¤")
						.build());
		log.info("Formato customizado: 00000.00 ¤");

		String customFormatted = customFormat.format(oneDollar);

		assertNotNull(customFormat);
		assertEquals("USD 1", oneDollar.toString());
		assertEquals("00001.00 US Dollar", customFormatted);

	}

}