package com.tang4j.core.support;

import com.baomidou.mybatisplus.annotation.DbType;
import com.tang4j.core.util.PropertiesUtil;

public class GenerateConfig {

    /**
     * 数据库访问路径
     */
    private String dbUrl;
    /**
     * 数据库类型
     */
    private DbType dbType;
    /**
     * 数据库登陆名
     */
    private String dbUser;
    /**
     * 数据库登密码
     */
    private String dbPassword;
    /**
     * 数据库驱动名称
     */
    private String dbDriverName;
    /**
     * 生成文件注释作者
     */
    private String authorName;
    /**
     * 生成文件所在包名
     */
    private String packageName;
    /**
     * 生成文件输出路径
     */
    private String outputDir;

    /**
     * 表名集合
     */
    private String[] tableNameArr;
    /**
     * model父类
     */
    private Class superEntityClass;
    /**
     * mapper父类
     */
    private Class superMapperClass;
    /**
     * service父类
     */
    private Class superServiceClass;
    /**
     * serviceImpl父类
     */
    private Class superServiceImplClass;

    /**
     * controller父类
     */
    private Class superControllerClass;


    public GenerateConfig() {
        dbUrl = PropertiesUtil.getProperty("spring.datasource.url");
        dbUser = PropertiesUtil.getProperty("spring.datasource.username");
        dbPassword = PropertiesUtil.getProperty("spring.datasource.password");
        dbDriverName = PropertiesUtil.getProperty("spring.datasource.driver-class-name");
        packageName = "com.tang4j";
        outputDir = "D:\\tang4j-generate";
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public GenerateConfig setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
        return this;
    }

    public DbType getDbType() {
        return dbType;
    }

    public GenerateConfig setDbType(DbType dbType) {
        this.dbType = dbType;
        return this;
    }

    public String getDbUser() {
        return dbUser;
    }

    public GenerateConfig setDbUser(String dbUser) {
        this.dbUser = dbUser;
        return this;
    }

    public String getDbDriverName() {
        return dbDriverName;
    }

    public GenerateConfig setDbDriverName(String dbDriverName) {
        this.dbDriverName = dbDriverName;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public GenerateConfig setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public GenerateConfig setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public GenerateConfig setOutputDir(String outputDir) {
        this.outputDir = outputDir;
        return this;
    }

    public String[] getTableNameArr() {
        return tableNameArr;
    }

    public GenerateConfig setTableNameArr(String[] tableNameArr) {
        this.tableNameArr = tableNameArr;
        return this;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public GenerateConfig setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
        return this;
    }

    public Class getSuperEntityClass() {
        return superEntityClass;
    }

    public GenerateConfig setSuperEntityClass(Class superEntityClass) {
        this.superEntityClass = superEntityClass;
        return this;
    }

    public Class getSuperMapperClass() {
        return superMapperClass;
    }

    public GenerateConfig setSuperMapperClass(Class superMapperClass) {
        this.superMapperClass = superMapperClass;
        return this;
    }

    public Class getSuperServiceClass() {
        return superServiceClass;
    }

    public GenerateConfig setSuperServiceClass(Class superServiceClass) {
        this.superServiceClass = superServiceClass;
        return this;
    }

    public Class getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public GenerateConfig setSuperServiceImplClass(Class superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
        return this;
    }

    public Class getSuperControllerClass() {
        return superControllerClass;
    }

    public GenerateConfig setSuperControllerClass(Class superControllerClass) {
        this.superControllerClass = superControllerClass;
        return this;
    }
}
