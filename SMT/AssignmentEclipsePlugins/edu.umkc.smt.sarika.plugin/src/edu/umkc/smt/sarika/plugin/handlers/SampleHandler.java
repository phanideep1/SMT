package edu.umkc.smt.sarika.plugin.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

import edu.umkc.smt.sarika.plugin.IGame;


/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}

	

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		/*IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"My Plug-ins",
				evaluateGreeterExtension());//Copy and replace.*/

		evaluateGreeterExtension();
		return null;
	}
  
	  private void evaluateGreeterExtension() {

			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor("edu.umkc.sarika.myplugin.tetris");//Change to fit your own
			try {
				for (IConfigurationElement e : config) {
					System.out.println("Evaluating extension");
					final Object o = e.createExecutableExtension("class");
						if (o instanceof IGame) {
						((IGame) o).getTetris();
					}
				}

			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
		
			}
	  }
}
