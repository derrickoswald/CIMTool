/*
 * This software is Copyright 2005,2006,2007,2008 Langdale Consultants.
 * Langdale Consultants can be contacted at: http://www.langdale.com.au
 */
package au.com.langdale.cimtoole.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import au.com.langdale.cimtoole.project.Task;
import au.com.langdale.util.Jobs;

public class NewRuleset extends Wizard implements INewWizard {
	
	private RuleWizardPage main = new RuleWizardPage();

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("New Validation Rule Set"); 
		main.setTitle(getWindowTitle());
		main.setDescription("Create a new set of rules to augment an OWL profile definition.");
		main.setType("split-rules");
		main.setSelected(selection);
	}
	
	@Override
    public void addPages() {
        addPage(main);        
    }
	
	@Override
	public boolean performFinish() {
		return Jobs.runInteractive(Task.createValidationRules(main.getFile(), main.isCopyDefault()? "cimtool-split":null), main.getFile().getParent(), getContainer(), getShell());
	}
}
