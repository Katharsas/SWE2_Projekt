package swe2.shared.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * @author jmothes
 */
@Embeddable
public class Money implements Serializable {

	final private BigDecimal amount;

	public Money(int euro) {
		this.amount = new BigDecimal(euro);
	}

	public Money(double euro) {
		this.amount = new BigDecimal(euro);
	}

	public Money(String euro) {
		Objects.requireNonNull(euro);
		this.amount = new BigDecimal(euro);
	}

	public Money(BigDecimal euro) {
		Objects.requireNonNull(euro);
		this.amount = euro;
	}

	public BigDecimal inEuro() {
		return amount;
	}

	public Money multiply(Money money) {
		Objects.requireNonNull(money);
		return new Money(this.amount.multiply(money.amount));
	}
}
