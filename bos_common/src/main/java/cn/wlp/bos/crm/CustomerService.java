
package cn.wlp.bos.crm;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "CustomerService", targetNamespace = "http://service.crm.bos.wlp.cn/")
@XmlSeeAlso({
        
})
public interface CustomerService {

    /**
     * @param arg0
     * @return returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findDecidedzoneIdByAddress", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindDecidedzoneIdByAddress")
    @ResponseWrapper(localName = "findDecidedzoneIdByAddressResponse", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindDecidedzoneIdByAddressResponse")
    public String findDecidedzoneIdByAddress(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * @param arg0
     * @return returns java.util.List<cn.wlp.bos.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListHasAssociation", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindListHasAssociation")
    @ResponseWrapper(localName = "findListHasAssociationResponse", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindListHasAssociationResponse")
    public List<Customer> findListHasAssociation(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * @return returns java.util.List<cn.wlp.bos.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindAllResponse")
    public List<Customer> findAll();

    /**
     * @param arg0
     * @return returns cn.wlp.bos.crm.service.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCustomerByTel", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindCustomerByTel")
    @ResponseWrapper(localName = "findCustomerByTelResponse", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindCustomerByTelResponse")
    public Customer findCustomerByTel(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * @return returns java.util.List<cn.wlp.bos.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListNotAssociation", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindListNotAssociation")
    @ResponseWrapper(localName = "findListNotAssociationResponse", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.FindListNotAssociationResponse")
    public List<Customer> findListNotAssociation();

    /**
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "assigncustomerstodecidedzone", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.Assigncustomerstodecidedzone")
    @ResponseWrapper(localName = "assigncustomerstodecidedzoneResponse", targetNamespace = "http://service.crm.bos.wlp.cn/", className = "cn.wlp.bos.crm.service.AssigncustomerstodecidedzoneResponse")
    public void assigncustomerstodecidedzone(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    List<Integer> arg1);

}
