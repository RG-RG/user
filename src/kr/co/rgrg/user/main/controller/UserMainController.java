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
import kr.co.rgrg.user.pagination.PaginationService;
import kr.co.rgrg.user.pagination.RangeVO;

@Controller
public class UserMainController {

	/**
	 * 메인화면을 보여주기 (+조회순or최신순 +검색)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/main.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String main(Model model, String sort, String search) {
		int int_page = 1;

		if (sort == null) { // null일 경우 기본값으로 input_date를 지정해줌
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

		// 페이지네이션
		int total_cnt = new PaginationService().getMainTotalCnt(rVO);
		model.addAttribute("cur_page", int_page);
		model.addAttribute("end_num", rVO.getEnd_num());
		model.addAttribute("total_cnt", total_cnt);
		
		System.out.println("--------------------------"+ rVO.getEnd_num() +"//"+ total_cnt+"//"+int_page);

		return "main/main";
	}

	@RequestMapping(value = "/main/main/{param_page}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String see_more(@PathVariable("param_page") String param_page, String sort, String search) {
		String json = null;
		int page = 1;
		if (param_page != null && !"".equals(param_page)) {
			page = Integer.parseInt(param_page);
			// page가 null이 아닐경우 int로 바꿔줌
		}
//		System.out.println("----------------------------------"+page);

		RangeVO rVO = new RangeVO(page, sort, "");

		if (search != null) {
			rVO.setColumn_value(search);
		}
		;
//		System.out.println("----------------------------------"+rVO);
		json = new UserMainService().getUserMoreList(rVO, page);

		return json;
	}
}
