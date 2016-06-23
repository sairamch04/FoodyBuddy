import java.util.List;
import com.FoodyBuddy.Model.Buyer;

public interface BuyerDAO{

	public List<Buyer> getAllBuyers();
	public Buyer getBuyer(int id);
	public boolean insertBuyer(Buyer buyer);
	public boolean updateBuyer(Buyer buyer);
	public boolean deleteBuyer(int id);
}