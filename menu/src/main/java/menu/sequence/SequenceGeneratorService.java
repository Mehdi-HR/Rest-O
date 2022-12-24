package menu.sequence;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public record SequenceGeneratorService(MongoOperations mongoOperations) {

    public long generateSequence(String seqName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(seqName));
        DatabaseSequence counter = mongoOperations.findAndModify(query,
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }


}
