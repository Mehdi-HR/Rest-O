package menu.item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, Long> {


}
