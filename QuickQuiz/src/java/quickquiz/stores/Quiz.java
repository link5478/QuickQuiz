/*
 * QuickQuiz is a database application allowing staff to manage
 * students to complete them.

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package quickquiz.stores;

import java.util.ArrayList;

/**
 *
 * @author Louis-Marie Matthews
 */
public class Quiz
{
    private String quizName_;
    private String description_;
    private String moduleId_;
    private String moduleName_;
    private String name_;
    private String staffId_;
    private String staffName_;
    private ArrayList<Question> questions_;
    
    
    
    // TODO: name and int limited to db's lengths?
    public Quiz(String quizName, String description, String moduleId, String moduleName,
                String staffName)
    {
        quizName_ = quizName;
        description_ = description;
        moduleId_ = moduleId;
        moduleName_ = moduleName;
        staffName_ = staffName;
        questions_ = new ArrayList<Question>();
    }
    
    
    
    public void addQuestion(Question question)
    {
        questions_.add(question);   
    }
    
    
    
    public Question getQuestion(int position)
    {
        return questions_.get(position);
    }
    
    
    
    public String getDescription()
    {
        return description_;
    }
    
    
    
    public String getModuleName()
    {
        return moduleName_;
    }
    
    
    
    public String getModuleId()
    {
        return moduleId_;
    }
    
    
    
    public String getQuizName()
    {
        return quizName_;
    }
    
    
    
    public String getStaffName()
    {
        return staffName_;
    }
    
    
    
    public void setDescription(String description)
    {
        description_ = description;
    }
    
    
    
    public void setModuleId(String moduleId)
    {
        moduleId_ = moduleId;
    }
    
    
    
    public void setQuizName(String quizName)
    {
        quizName_ = quizName;
    }
    
    
    
    public void setStaffName(String staffName)
    {
        staffName_ = staffName;
    }
    
    
   
    public void setModuleName(String moduleName)
    {
        moduleName_ = moduleName;
    }
}
