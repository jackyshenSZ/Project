package com.paradm.kaptcha.configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jacky.shen
 * @create data 2020/4/27
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(KaptchaProperties.class)
@ComponentScan({"com.paradm.kaptcha.controller"})
public class KaptchaAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnProperty(prefix = KaptchaProperties.KAPTCHA_PREFIX, value = "enabled", havingValue = "true")
  public DefaultKaptcha kaptcha(KaptchaProperties kaptchaProperties) {
    Config config = new Config(kaptchaProperties.getKaptchaProperties());
    DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
    defaultKaptcha.setConfig(config);
    return defaultKaptcha;
  }
}
