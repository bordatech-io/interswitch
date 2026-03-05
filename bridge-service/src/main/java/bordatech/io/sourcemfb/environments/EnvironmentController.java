package bordatech.io.sourcemfb.environments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentController {
    @Autowired
    private Environment env;

}
