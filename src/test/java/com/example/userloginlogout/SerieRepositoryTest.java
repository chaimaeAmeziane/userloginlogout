package com.example.userloginlogout;

import com.example.userloginlogout.models.Serie;
import com.example.userloginlogout.models.User;
import com.example.userloginlogout.repository.SerieRepository;
import com.example.userloginlogout.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class SerieRepositoryTest {
    @Autowired
    private SerieRepository srepo ;

    @Autowired
    private UserRepository urepo ;

    @Test
    public void testCreateSerie()
    {
        User user1 = new User();
        user1.setEmail("test.serie@gmail.com");
        user1.setPassword("test");
        user1.setFirstName("testSerie");
        user1.setLastName("testSerie");
        User savedUser = urepo.save(user1);
        Serie savedSerie = srepo.save(new Serie("Serie1", "This is a Serie 1 test",user1));
        assertThat(savedSerie.getId()).isGreaterThan(0);
    }

    @Test
    public void testDisplaySeriesById()
    {
            int id = 4;
            List<Serie> listeTest = srepo.findSeriesByUser(id);
            assertThat(listeTest);
            for(int i=0 ; i<listeTest.size();i++)
            {
                System.out.println(listeTest.get(i).getTitre());
            }
    }
}
