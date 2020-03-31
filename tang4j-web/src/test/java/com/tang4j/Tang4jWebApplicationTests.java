package com.tang4j;

import com.baomidou.mybatisplus.annotation.DbType;
import com.tang4j.core.controller.AbstractController;
import com.tang4j.core.mapper.AbstractMapper;
import com.tang4j.core.model.AbstractModel;
import com.tang4j.core.service.AbstractService;
import com.tang4j.core.service.impl.AbstractServiceImpl;
import com.tang4j.core.support.GenerateConfig;
import com.tang4j.core.util.GenerateCodeUtil;
import com.tang4j.model.SysAdmin;
import com.tang4j.service.SysAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class Tang4jWebApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SysAdminService sysAdminService;

    /**
     * 测试自动生成代码文件
     */
    @Test
    void generateLoads() {
        GenerateConfig generateConfig = new GenerateConfig();
        generateConfig
                .setDbType(DbType.MYSQL)
                .setAuthorName("saber")
                .setTableNameArr(new String[]{"sys_admin"})
                .setSuperEntityClass(AbstractModel.class)
                .setSuperServiceClass(AbstractService.class)
                .setSuperControllerClass(AbstractController.class)
                .setSuperMapperClass(AbstractMapper.class)
                .setSuperServiceImplClass(AbstractServiceImpl.class);


        GenerateCodeUtil.generateByTables(generateConfig);

    }

    /**
     * insert方法
     */

    @Test
    void testJdbc() {
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setPassword(new BCryptPasswordEncoder().encode("123456"));
        sysAdmin.setId("1");
        sysAdminService.update(sysAdmin);
    }


}
