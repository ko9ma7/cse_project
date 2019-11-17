
public abstract class InternetShoppingOrder {
	public final void processOrder() { 
		doSelect();
		doPayment();
		if (isQuickDelivery()) doQuickDelivery();
		else doDelivery();
	}
	protected final void doSelect() { System.out.println("구입 물건 선택"); }
	protected final void doDelivery() { System.out.println("택배로 전달"); }
	protected abstract void doPayment();
	protected boolean isQuickDelivery() { return true; }
	protected void doQuickDelivery() { System.out.println("로켓배송으로 전달"); }
}
