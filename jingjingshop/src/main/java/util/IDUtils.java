package util;

/**
 * @desc:id生成器
 * @author:yue
 * @date:2019.7.2
 */
public class IDUtils {

	//ID生成器
	private static IdWorker idWorker = new IdWorker();
	
	/**
	 * 订单Id生成器
	 *   前两位13+后八位随机数字 
	 * @return
	 */
	public static Long generateOrderID(){
		String nextId = idWorker.nextId().toString();
		String idStr = nextId.substring(5,7)+nextId.substring(nextId.toString().length()-6);
		String orderIdStr = "13"+idStr;
		return Long.parseLong(orderIdStr);
	}
	
	/**
	 * 商品ID生成器
	 *   11位随机数字
	 * @return
	 */
	public static Long generateGoodsID(){
		String goodsIDStr = idWorker.nextId().toString().substring(8);
		return Long.parseLong(goodsIDStr);
	}
	
	/**
	 * 购物车ID生成器
	 *   11位随机数字
	 * @return
	 */
	public static Long generateCartID(){
		String goodsIDStr = idWorker.nextId().toString().substring(8);
		return Long.parseLong(goodsIDStr);
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			Long generateOrderID = generateOrderID();
			System.out.println("generateOrderID:"+generateOrderID);
		}
	}
}
