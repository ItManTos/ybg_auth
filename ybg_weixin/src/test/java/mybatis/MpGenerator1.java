package mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MpGenerator1 {

	public static void main(String[] args) throws InterruptedException {
		// 用来获取Mybatis-Plus.properties文件的配置信息
		final ResourceBundle rb = ResourceBundle.getBundle("Mybatis-Plus");

		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(rb.getString("OutputDir"));
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(true);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setAuthor(rb.getString("author"));
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		/*
		 * dsc.setTypeConvert(new MySqlTypeConvert(){ // 自定义数据库表字段类型转换【可选】
		 *
		 * @Override public DbColumnType processTypeConvert(String fieldType) {
		 * System.out.println("转换类型：" + fieldType); return
		 * super.processTypeConvert(fieldType); } });
		 */
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUrl(rb.getString("url"));
		dsc.setUsername(rb.getString("userName"));
		dsc.setPassword(rb.getString("password"));
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
		strategy.setTablePrefix(new String[] { "i_", "b_", "r_", "t_", "p_" });// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(rb.getString("tableName").split(",")); // 需要生成的表
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父类
		// strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定义 mapper 父类
		// strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
		// 自定义 service 父类
		// strategy.setSuperServiceClass("com.baomidou.demo.TestService");
		// 自定义 service 实现类父类
		// strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
		// 自定义 controller 父类
		// strategy.setSuperControllerClass("com.baomidou.demo.TestController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuilderModel(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(rb.getString("parent"));
		// pc.setModuleName("tbldept");//模块名称，单独生成模块时使用！！！！！！！！！！！
		pc.setController("controller." + rb.getString("className"));
		pc.setService("service." + rb.getString("className"));
		pc.setServiceImpl("service." + rb.getString("className") + ".impl");
		pc.setEntity("entity." + rb.getString("className"));
		pc.setMapper("dao." + rb.getString("className"));
		mpg.setPackageInfo(pc);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("abc", getConfig().getGlobalConfig().getAuthor() + "-mp");
				setMap(map);
			}
		};

		// 自定义 xxList.jsp 生成
		List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
		/*
		 * focList.add(new FileOutConfig("/template/list.jsp.vm") {
		 *
		 * @Override public String outputFile(TableInfo tableInfo) { // 自定义输入文件名称 return
		 * "E:/workspace/ssmp-crud2/src/main/webapp/WEB-INF/" +
		 * tableInfo.getEntityName() + ".jsp"; } }); cfg.setFileOutConfigList(focList);
		 * mpg.setCfg(cfg);
		 */

		// 调整 xml 生成目录演示
		focList.add(new FileOutConfig("/word/templates/mapper.xml.vm") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				return rb.getString("OutputDirXml") + "/mapper/" + rb.getString("className") + "/"
						+ tableInfo.getEntityName() + ".xml";
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);

		// 关闭默认 xml 生成，调整生成 至 根目录
		TemplateConfig tc = new TemplateConfig();
		tc.setXml(null);
		mpg.setTemplate(tc);

		// 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
		// 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		// TemplateConfig tc = new TemplateConfig();
		tc.setController("/word/templates/controller.java.vm");
		tc.setEntity("/word/templates/entity.java.vm");
		tc.setMapper("/word/templates/mapper.java.vm");
		// tc.setXml("...");
		tc.setService("/word/templates/service.java.vm");
		tc.setServiceImpl("/word/templates/serviceImpl.java.vm");
		// 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
		// mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();
	}
}