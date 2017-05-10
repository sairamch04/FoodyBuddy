import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.FoodyBuddy.Service.OrderDishService;
import com.FoodyBuddy.Service.Impl.OrderDishServiceImpl;
import com.mysql.jdbc.log.LogFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/hibernate.cfg.xml")
public class OrderDishServiceImplTest {
	static Log log = LogFactory.getLog(OrderDishServiceImplTest.class.getName());
	
	public void testSampleMain(){
		testSample(1,1);
		testSample(1,-1);
		testSample(1,0);
		testSample(1,9999999);
	}
	
	@Test
	public void testSample(int orderDishId, int updatedQuantity) {
		try{
			OrderDishService orderDishService = new OrderDishServiceImpl(SessionFactoryUtils.getSessionFactory());
			insertRequiredData(orderDishId, updatedQuantity,SessionFactoryUtils.getSessionFactory());
			orderDishService.updateOrderDishQuantity(orderDishId, updatedQuantity);
			assertTrue(true);
		}
		catch(Exception ex){
			assertTrue(false);
			log.error(ex);				
		}
	}
	private void insertRequiredData(int orderDishId, int updatedQuantity, SessionFactory sessionFactory){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//create dependent models before placing order
		Dish dish = new Dish();
		Order order = new Order();
		order.setorderId("1");
		dish.setName("Biryani");
		dish.setPrice(100);
		dish.setQuantityAvailable(10);
		DishDAO dishDAO = new DishDAOImpl(session);
		OrderDAO orderDAO = new OrderDAO(session);
		OrderDAO.insert(order);
		dishDAO.insert(dish);
		session.getTransaction().commit();
		session.close();
		
	}
}