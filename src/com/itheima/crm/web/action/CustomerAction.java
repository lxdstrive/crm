package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;

/**
 * 客户管理的action
 *
 * @author BJXT-LXD
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    private Customer customer = new Customer();

    @Override
    public Customer getModel() {
        return customer;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //使用set方法的方式来接收数据
    private Integer currPage = 1;

    public void setCurrPage(Integer currPage) {
        if (currPage == null) {
            currPage = 1;
        }
        this.currPage = currPage;
    }

    private Integer pageSize = 3;

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 3;
        }
        this.pageSize = pageSize;
    }

    /**
     * 文件上传提供的三个属性  <input type="file" name="upload"/>
     * 字符串类型   上传项名称 + FileName;
     * file类型    上传项名称
     * 字符串类型   上传项名称 + ContentType
     */
    private String uploadFileName; //文件名称
    private File upload;            //上传文件
    private String uploadContentType; //文件类型

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * 保存客户
     */
    public String save() throws IOException {
        //上传文件
        if (upload != null) {
            //说明选择了文件，进行文件的上传
            //设置文件上传的路径
            String path = "C:/upload";
            //一个目录下存放的相同文件名 ： 随机文件名
            String uuidFileName = UploadUtils.getUuitFileName(uploadFileName);

            //一个目录下存放的文件过多 : 文件分离
            String realPath = UploadUtils.getPath(uuidFileName);
            String url = path + realPath;
            //创建目录
            File file = new File(url);
            if (!file.exists()) {
                //如果目录不存在，新建
                file.mkdirs();
            }
            //文件上传
            File dictFile = new File(url + "/" + uuidFileName);
            FileUtils.copyFile(upload, dictFile);

            //设置image的属性的值
            customer.setCust_image(url + "/" + uuidFileName);
        }
        //保存客户
        customerService.save(customer);
        return "saveSuccess";
    }

    /**
     * 保存客户UI:跳转到添加客户的界面
     */
    public String saveUI() {
        return "saveUI";
    }

    /**
     * 分页查询客户的方法
     */
    public String findAll() {
        //最好使用detachedCriteria对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        //设置条件
        if (customer.getCust_name() != null) {
            //输入了名称
            detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }
        if (customer.getBaseDictSource() != null) {
            if (customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())) {

                detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
            }
        }
        if (customer.getBaseDictIndustry() != null) {
            if (customer.getBaseDictIndustry().getDict_id() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())) {

                detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
            }
        }
        if (customer.getBaseDictLevel() != null) {
            if (customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())) {

                detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
            }
        }

        //业务层查询
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);

        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    /**
     * 删除客户
     */
    public String delete() {
        //先查询，再删除
        customer = customerService.findById(customer.getCust_id());
        //删除图片
        if (customer.getCust_image() != null) {
            File file = new File(customer.getCust_image());
            if (file.exists()) {
                file.delete();
            }
        }
        //删除客户
        customerService.delete(customer);
        return "deleteSuccess";
    }

    /**
     * 编辑客户的方法
     */
    public String edit() {
        //根据id查询数据，然后回显到界面
        customer = customerService.findById(customer.getCust_id());
        //将customer传递到界面 两种方式，一：手动压栈，二种，不用管他，因为模型驱动的对象，默认在值栈里
        //如果使用第一种，回显数据的时候，<s:property value="cust_name"/>
        //如果使用第二种，回显数据的时候，<s:property value="model.cust_name"/>
        //ActionContext.getContext().getValueStack().push(customer);
        return "editSuccess";
    }

    /**
     * 更新数据
     */
    public String update() throws IOException {
        //文件项是否已经被选择，如果选择了，就删除原有文件，保存新文件，如果没有选择，使用原文件即可
        if (upload != null) {
            //说明选择了新文件
            //删除原有文件
            String cust_image = customer.getCust_image();
            if (cust_image != null || !"".equals(cust_image)) {
                File file = new File(cust_image);
                if (file.exists()) {
                    file.delete();
                }
            }

            //说明选择了文件，进行文件的上传
            //设置文件上传的路径
            String path = "C:/upload";
            //一个目录下存放的相同文件名 ： 随机文件名
            String uuidFileName = UploadUtils.getUuitFileName(uploadFileName);

            //一个目录下存放的文件过多 : 文件分离
            String realPath = UploadUtils.getPath(uuidFileName);
            String url = path + realPath;
            //创建目录
            File file = new File(url);
            if (!file.exists()) {
                //如果目录不存在，新建
                file.mkdirs();
            }
            //文件上传
            File dictFile = new File(url + "/" + uuidFileName);
            FileUtils.copyFile(upload, dictFile);
            customer.setCust_image(url + "/" + uuidFileName);
        }
        customerService.update(customer);
        return "updateSuccess";
    }
}
