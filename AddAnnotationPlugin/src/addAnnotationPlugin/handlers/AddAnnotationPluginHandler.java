package addAnnotationPlugin.handlers;

import java.util.Optional;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * アノテーションの追加
 *
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class AddAnnotationPluginHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IFile source = Optional.of(event).map(HandlerUtil::getActiveEditorInput)
				.map(input -> input.getAdapter(IFile.class)).orElse(null);

		if (source == null) {
			return null;
		}

		addAnnotation.Main addAnnotationMain = new addAnnotation.Main();
		addAnnotationMain.exec(source.getLocation().toOSString());

		return null;
	}
}
