import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

    @Before
    public void setup()
    {
        this.tranaction = new Transaction(this.obReservation, this.payType, this.payMethod);
    }

    @Test
    public void validReservation()
    {

    }

}
