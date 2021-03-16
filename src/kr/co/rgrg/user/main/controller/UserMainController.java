package kr.co.rgrg.user.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rgrg.user.main.domain.UserMainDomain;
import kr.co.rgrg.user.main.service.UserMainService;
import kr.co.rgrg.user.pagination.RangeVO;

@Controller
public class UserMainController {
	
	   /**
	    * ����ȭ���� �����ֱ� (+��ȸ��or�ֽż� +�˻�)
	    * @param model
	    * @return
	    */
	   @RequestMapping(value = "/main/main", method = RequestMethod.GET)
	   public String main(Model model, String sort, String search) {
	      int int_page = 1;

	      if (sort == null) { // null�� ��� �⺻������ input_date�� ��������
	         sort = "input_date";
	      };

	      RangeVO rVO = new RangeVO(int_page, sort, "");

	      if (search != null) {
	         rVO.setColumn_value(search);
	      };

	      List<UserMainDomain> main_list = new UserMainService().getUserMainList(rVO);
	      model.addAttribute("main_list", main_list);
	      model.addAttribute("sort", sort);
	      model.addAttribute("search", search);

	      return "main/main";
	   }

	@RequestMapping(value="/main/main/{param_page}", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String see_more(@PathVariable("param_page") String param_page, String sort, String search) {
		String json=null;
		int page = 1;
		if(param_page != null && !"".equals(param_page)) {
			page = Integer.parseInt(param_page); 
			//page�� null�� �ƴҰ�� int�� �ٲ��� 
		}
//		System.out.println("----------------------------------"+page);
		
		RangeVO rVO = new RangeVO(page, sort, "");
		
	    if (search != null) {
		         rVO.setColumn_value(search);
		};
//		System.out.println("----------------------------------"+rVO);
		json = new UserMainService().getUserMoreList(rVO);
		
		return json;
	}
}
