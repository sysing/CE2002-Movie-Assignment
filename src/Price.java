public class Price {
	private String PriceType;
	private double Price;

	public Price(String PriceType,double Price){
		this.PriceType=PriceType;
		this.Price=Price;
	}

	//get set method
	public String getPriceType() { return PriceType ; }
	public double getPrice() { return Price ; }
	
	public void setPriceType(String PriceType) { this.PriceType=PriceType; }
	public void setPrice(double Price) { this.Price=Price; }

	public boolean equals(Object o) {
		if (o instanceof Price) {
			Price p = (Price)o;
			return (getPriceType().equals(p.getPriceType()));
		}
		return false;
	}
}