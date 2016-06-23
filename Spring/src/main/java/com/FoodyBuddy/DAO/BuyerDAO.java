import java.util.List;
import com.FoodyBuddy.Model.Buyer;

public interface BuyerDAO{

	public List<Buyer> getAllBuyers();
	public Buyer getBuyer(int id);
	public void insertBuyer(Buyer buyer);
	public void updateBuyer(Buyer buyer);
	public void deleteBuyer(int id);
}