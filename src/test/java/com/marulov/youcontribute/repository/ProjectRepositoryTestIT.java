package com.marulov.youcontribute.repository;

import com.marulov.youcontribute.model.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(initializers = ProjectRepositoryTestIT.Initializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
@Testcontainers
public class ProjectRepositoryTestIT {

    public static final DockerImageName MYSQL_IMAGE = DockerImageName.parse("mysql:5.7");

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>(MYSQL_IMAGE);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void it_should_find_all_repositories() throws Exception {
        //given
        Project Project1 = Project.builder().repository("repo1").organization("org1").build();
        Project Project2 = Project.builder().repository("repo2").organization("org2").build();

        projectRepository.saveAll(Arrays.asList(Project1, Project2));
        testEntityManager.flush();

        //when
        List<Project> projects = projectRepository.findAll();

        //then
        then(projects.size()).isEqualTo(2);
        Project project1 = projects.get(0);
        Project project2 = projects.get(1);
        then(project1.getRepository()).isEqualTo("repo1");
        then(project1.getOrganization()).isEqualTo("org1");
        then(project2.getRepository()).isEqualTo("repo2");
        then(project2.getOrganization()).isEqualTo("org2");
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("spring.datasource.url=" + mysql.getJdbcUrl(),
                            "spring.datasource.username=" + mysql.getUsername(),
                            "spring.datasource.password=" + mysql.getPassword())
                    .applyTo(applicationContext.getEnvironment());
        }
    }
}