package cn.tellsea.sunday.common.config;

import cn.tellsea.sunday.common.properties.BaseProperties;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger2 api 配置
 *
 * @author: Tellsea
 * @date : 2020/3/3
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private BaseProperties properties;

    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .globalOperationParameters(getGlobalParam())
                .enable(properties.getSwagger().isEnabled())
                .select()
                .apis(basePackage(properties.getSwagger().getControllerUrl()))
                .paths(PathSelectors.any())
                .build();
    }

    private List<Parameter> getGlobalParam() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder().name("token")
                .description("用户登陆令牌")
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                .required(false)
                .build());
        return parameters;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getSwagger().getTitle())
                .description(properties.getSwagger().getDescription())
                .termsOfServiceUrl(properties.getSwagger().getTermsOfServiceUrl())
                .contact(new Contact(properties.getSwagger().getAuthor(), properties.getSwagger().getUrl(), properties.getSwagger().getEmail()))
                .version(properties.getSwagger().getVersion())
                .build();
    }

    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(StringPool.COMMA)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }
}
