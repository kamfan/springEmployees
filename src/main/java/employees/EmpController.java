package employees;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmpController {
    private List<Emp> list;

    public EmpController() {
        list = new ArrayList<>();
        list.add(new Emp(1,"Jakub",35000f,"Trener","test.kurs.123123@gmail.com"));
        list.add(new Emp(2,"Adam",25000f,"Siatkarz","test.kurs.123123@gmail.com"));
        list.add(new Emp(3,"Robert",55000f,"YouTuber","test.kurs.123123@gmail.com"));
    }

    @RequestMapping("/empform")
    public ModelAndView showform(){
        return new ModelAndView("empform","command", new Emp());
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("emp") Emp emp){
        if(emp.getId() < 0) {
            System.out.println("New emp");
            emp.setId(list.size()+1);
            list.add(emp);
        } else {
            Emp emp1 = getEmpById(emp.getId());
            emp1.setDesignation(emp.getDesignation());
            emp1.setName(emp.getName());
            emp1.setSalary(emp.getSalary());
            emp1.setEmail(emp.getEmail());
        }
        System.out.println(emp.getName()+" "+emp.getSalary()+" "+emp.getDesignation());
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public ModelAndView delete(@RequestParam String id){
        //Stream vs list
        /*
        for (Emp one : list) {
            if(one.getId() == Integer.parseInt(id))
            list.remove(one);
        }

        Emp emp = list.stream().filter(f -> f.getId() == Integer.parseInt(id)).findFirst().get();
        list.remove(emp);
         */
        list.remove(getEmpById(Integer.parseInt(id)));
        return new ModelAndView("redirect:/viewemp");
    }

    /*    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@ModelAttribute("emp") Emp emp) {
        for (Emp one : list) {
            if (one.getId() == Integer.parseInt(id))
                list.remove(one);
        }

        return new ModelAndView("redirect:/viewemp");
    }*/

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public ModelAndView edit(@RequestParam String id){
        Emp emp = getEmpById(Integer.parseInt(id));
        return new ModelAndView("empform","command", emp);
    }

    @RequestMapping(value="/test", method=RequestMethod.POST)
    public ModelAndView test(){
        System.out.println("Test");
        return new ModelAndView("redirect:/viewemp");
    }

    @RequestMapping("/viewemp")
    public ModelAndView viewemp(){
        return new ModelAndView("viewemp","list", list);
    }

    private Emp getEmpById(@RequestParam int id) {
        return list.stream().filter(f -> f.getId() == id).findFirst().get();
    }
}