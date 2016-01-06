package swe2.shared.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * @author jmothes
 */
@SuppressWarnings("serial")
@Embeddable
public class Money implements Serializable {

	final private BigDecimal amount;

	@Deprecated
	protected Money() {this(0);}
	
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
	
	@Override
	public int hashCode() {
		return amount.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;
		return amount.equals(((Money)obj).amount);
	}

	public String inEuro() {
		return amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public Money add(Money money) {
		Objects.requireNonNull(money);
		return new Money(this.amount.add(money.amount));
	}

	public Money multiply(Money money) {
		Objects.requireNonNull(money);
		return new Money(this.amount.multiply(money.amount));
	}
}
