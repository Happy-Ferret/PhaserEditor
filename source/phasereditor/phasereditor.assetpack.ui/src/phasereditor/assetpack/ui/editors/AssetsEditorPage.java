//// The MIT License (MIT)
////
//// Copyright (c) 2015 Arian Fornaris
////
//// Permission is hereby granted, free of charge, to any person obtaining a
//// copy of this software and associated documentation files (the
//// "Software"), to deal in the Software without restriction, including
//// without limitation the rights to use, copy, modify, merge, publish,
//// distribute, sublicense, and/or sell copies of the Software, and to permit
//// persons to whom the Software is furnished to do so, subject to the
//// following conditions: The above copyright notice and this permission
//// notice shall be included in all copies or substantial portions of the
//// Software.
////
//// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
//// OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
//// MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN
//// NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
//// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
//// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
//// USE OR OTHER DEALINGS IN THE SOFTWARE.
// package phasereditor.assetpack.ui.editors;
//
// import java.beans.PropertyChangeEvent;
// import java.beans.PropertyChangeListener;
// import java.util.ArrayList;
// import java.util.List;
//
// import org.eclipse.core.resources.IFile;
// import org.eclipse.core.runtime.CoreException;
// import org.eclipse.core.runtime.QualifiedName;
// import org.eclipse.jface.dialogs.IInputValidator;
// import org.eclipse.jface.dialogs.InputDialog;
// import org.eclipse.jface.util.LocalSelectionTransfer;
// import org.eclipse.jface.viewers.ArrayContentProvider;
// import org.eclipse.jface.viewers.ISelection;
// import org.eclipse.jface.viewers.ISelectionChangedListener;
// import org.eclipse.jface.viewers.IStructuredSelection;
// import org.eclipse.jface.viewers.SelectionChangedEvent;
// import org.eclipse.jface.viewers.StructuredSelection;
// import org.eclipse.jface.viewers.TreeViewer;
// import org.eclipse.jface.window.Window;
// import org.eclipse.swt.SWT;
// import org.eclipse.swt.custom.StackLayout;
// import org.eclipse.swt.dnd.DND;
// import org.eclipse.swt.dnd.DragSourceAdapter;
// import org.eclipse.swt.dnd.DragSourceEvent;
// import org.eclipse.swt.dnd.Transfer;
// import org.eclipse.swt.events.DisposeEvent;
// import org.eclipse.swt.events.DisposeListener;
// import org.eclipse.swt.events.KeyAdapter;
// import org.eclipse.swt.events.KeyEvent;
// import org.eclipse.swt.events.SelectionAdapter;
// import org.eclipse.swt.events.SelectionEvent;
// import org.eclipse.swt.layout.GridData;
// import org.eclipse.swt.layout.GridLayout;
// import org.eclipse.swt.layout.RowData;
// import org.eclipse.swt.layout.RowLayout;
// import org.eclipse.swt.widgets.Button;
// import org.eclipse.swt.widgets.Composite;
// import org.eclipse.swt.widgets.Label;
// import org.eclipse.swt.widgets.Tree;
// import org.eclipse.ui.dialogs.ListDialog;
// import org.eclipse.ui.forms.IManagedForm;
// import org.eclipse.ui.forms.editor.FormEditor;
// import org.eclipse.ui.forms.editor.FormPage;
// import org.eclipse.ui.forms.widgets.ScrolledForm;
//
// import phasereditor.assetpack.core.AssetFactory;
// import phasereditor.assetpack.core.AssetGroupModel;
// import phasereditor.assetpack.core.AssetModel;
// import phasereditor.assetpack.core.AssetPackModel;
// import phasereditor.assetpack.core.AssetSectionModel;
// import phasereditor.assetpack.core.AssetType;
// import phasereditor.assetpack.core.AtlasAssetModel;
// import phasereditor.assetpack.core.AudioAssetModel;
// import phasereditor.assetpack.core.AudioSpriteAssetModel;
// import phasereditor.assetpack.core.BinaryAssetModel;
// import phasereditor.assetpack.core.BitmapFontAssetModel;
// import phasereditor.assetpack.core.ImageAssetModel;
// import phasereditor.assetpack.core.PhysicsAssetModel;
// import phasereditor.assetpack.core.ScriptAssetModel;
// import phasereditor.assetpack.core.SpritesheetAssetModel;
// import phasereditor.assetpack.core.TextAssetModel;
// import phasereditor.assetpack.core.TilemapAssetModel;
// import phasereditor.assetpack.ui.AssetLabelProvider;
// import phasereditor.assetpack.ui.AssetPackUI;
// import phasereditor.assetpack.ui.AssetsContentProvider;
// import phasereditor.ui.PhaserEditorUI;
//
// public class AssetsEditorPage extends FormPage {
//
// TreeViewer _allAssetsViewer;
// private Button _addSectionButton;
// private Button _removeButton;
// private Button _addAssetButton;
// private Button _moveButton;
// private ImageAssetEditorComp _imageAssetEditorComp;
// private Composite _editorsContainer;
// private EmptyAssetEditorComp _emptyAssetEditorComp;
// private SpritesheetAssetEditorComp _spritesheetAssetEditorComp;
// private AudioAssetEditorComp _audioAssetEditorComp;
// private AudioSpriteAssetEditorComp _audioSpriteEditorComp;
// protected PropertyChangeListener _assetKeyListener;
// private AssetSectionEditorComp _sectionEditorComp;
// private AssetGroupEditorComp _groupEditorComp;
// private TilemapAssetEditorComp _tilemapAssetEditorComp;
// private BitmapFontAssetEditorComp _bitmapFontAssetEditorComp;
// private PhysicsAssetEditorComp _physicsAssetEditorComp;
// private AtlasAssetEditorComp _atlasAssetEditorComp;
// private TextAssetEditorComp _textAssetEditorComp;
// private BinaryAssetEditorComp _binaryAssetEditorComp;
// private ScriptAssetEditorComp _scriptAssetEditorComp;
// private Object _editingElement;
//
// /**
// * Create the form page.
// *
// * @param id
// * @param title
// */
// public AssetsEditorPage(String id, String title) {
// super(id, title);
// }
//
// /**
// * Create the form page.
// *
// * @param editor
// * @param id
// * @param title
// * @wbp.parser.constructor
// * @wbp.eval.method.parameter id "Some id"
// * @wbp.eval.method.parameter title "Some title"
// */
// public AssetsEditorPage(FormEditor editor, String id, String title) {
// super(editor, id, title);
// }
//
// /**
// * Create contents of the form.
// *
// * @param managedForm
// */
// @Override
// protected void createFormContent(IManagedForm managedForm) {
// managedForm.getForm().getBody().setLayout(new GridLayout(2, true));
//
// _label_1 = new Label(managedForm.getForm().getBody(), SWT.NONE);
// _label_1.setText("Assets");
//
// _label_2 = new Label(managedForm.getForm().getBody(), SWT.NONE);
// _label_2.setText("Details");
//
// Composite composite = new Composite(managedForm.getForm().getBody(),
//// SWT.NONE);
// composite.setLayout(new GridLayout(2, false));
// GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
// gd_composite.heightHint = 50;
// composite.setLayoutData(gd_composite);
//
// Label _label = new Label(composite, SWT.WRAP);
// _label.setText(
// "Declare the assets of your game here. You can drag them to the Preview
//// window. The keys defined here are available (as auto-completion proposals)
//// in the Java Script editor.");
// GridData gd__label = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
// gd__label.widthHint = 20;
// _label.setLayoutData(gd__label);
//
// _allAssetsViewer = new TreeViewer(composite, SWT.BORDER | SWT.MULTI);
// _allAssetsViewer.setAutoExpandLevel(1);
// Tree tree = _allAssetsViewer.getTree();
// tree.addKeyListener(new KeyAdapter() {
// @Override
// public void keyReleased(KeyEvent e) {
// if (e.character == SWT.DEL) {
// delPressed();
// }
// }
// });
// GridData gd_tree = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
// gd_tree.heightHint = 50;
// tree.setLayoutData(gd_tree);
// _allAssetsViewer.setLabelProvider(new AssetLabelProvider());
// _allAssetsViewer.setContentProvider(new AssetsContentProvider());
//
// Composite composite_2 = new Composite(composite, SWT.NONE);
// RowLayout rl_composite_2 = new RowLayout(SWT.VERTICAL);
// rl_composite_2.marginTop = 0;
// rl_composite_2.marginRight = 0;
// rl_composite_2.marginLeft = 0;
// rl_composite_2.marginBottom = 0;
// rl_composite_2.fill = true;
// composite_2.setLayout(rl_composite_2);
// composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1,
//// 1));
//
// _addAssetButton = new Button(composite_2, SWT.NONE);
// _addAssetButton.setText("Add Asset");
// _addAssetButton.addSelectionListener(new SelectionAdapter() {
// @Override
// public void widgetSelected(SelectionEvent e) {
// openNewAssetDialog();
// }
// });
//
// _addSectionButton = new Button(composite_2, SWT.NONE);
// _addSectionButton.setText("Add Section");
// _addSectionButton.addSelectionListener(new SelectionAdapter() {
// @Override
// public void widgetSelected(SelectionEvent e) {
// openNewSectionDialog();
// }
// });
// _addSectionButton.setLayoutData(new RowData(100, SWT.DEFAULT));
//
// _previewButton = new Button(composite_2, SWT.NONE);
// _previewButton.setText("Preview");
// _previewButton.setToolTipText("Open the selected asset in the Preview
//// window.");
// _previewButton.addSelectionListener(new SelectionAdapter() {
// @Override
// public void widgetSelected(SelectionEvent e) {
// previewAsset();
// }
// });
//
// Composite composite_3 = new Composite(composite_2, SWT.NONE);
//
// _moveButton = new Button(composite_2, SWT.NONE);
// _moveButton.setText("Move");
// _moveButton.addSelectionListener(new SelectionAdapter() {
// @Override
// public void widgetSelected(SelectionEvent e) {
// moveAssetElement();
// }
// });
//
// _removeButton = new Button(composite_2, SWT.NONE);
// _removeButton.setText("Remove");
// _removeButton.addSelectionListener(new SelectionAdapter() {
// @Override
// public void widgetSelected(SelectionEvent e) {
// removeAssetElement();
// }
// });
//
// _editorsContainer = new Composite(managedForm.getForm().getBody(), SWT.NONE);
// _editorsContainer.setLayout(new StackLayout());
// GridData gd_editorsContainer = new GridData(SWT.FILL, SWT.FILL, true, false,
//// 1, 1);
// gd_editorsContainer.heightHint = 400;
// gd_editorsContainer.minimumWidth = 200;
// _editorsContainer.setLayoutData(gd_editorsContainer);
//
// _emptyAssetEditorComp = new EmptyAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _imageAssetEditorComp = new ImageAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _spritesheetAssetEditorComp = new
//// SpritesheetAssetEditorComp(_editorsContainer, SWT.NONE);
//
// _audioAssetEditorComp = new AudioAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _audioSpriteEditorComp = new AudioSpriteAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _sectionEditorComp = new AssetSectionEditorComp(_editorsContainer, SWT.NONE);
//
// _groupEditorComp = new AssetGroupEditorComp(_editorsContainer, SWT.NONE);
//
// _tilemapAssetEditorComp = new TilemapAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _bitmapFontAssetEditorComp = new BitmapFontAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _physicsAssetEditorComp = new PhysicsAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _atlasAssetEditorComp = new AtlasAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _textAssetEditorComp = new TextAssetEditorComp(_editorsContainer, SWT.NONE);
//
// _binaryAssetEditorComp = new BinaryAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// _scriptAssetEditorComp = new ScriptAssetEditorComp(_editorsContainer,
//// SWT.NONE);
//
// afterCreatedWidgets();
// }
//
// protected void previewAsset() {
// Object elem = ((IStructuredSelection)
//// _allAssetsViewer.getSelection()).getFirstElement();
// if (elem instanceof AssetModel) {
// PhaserEditorUI.openPreview(elem);
// }
// }
//
// protected void delPressed() {
// if (_removeButton.isEnabled()) {
// removeAssetElement();
// }
// }
//
// private void afterCreatedWidgets() {
//
// AssetPackModel model = getModel();
// _allAssetsViewer.setInput(model);
// updateUIFromSelection();
// _allAssetsViewer.addSelectionChangedListener(new ISelectionChangedListener()
//// {
//
// @Override
// public void selectionChanged(SelectionChangedEvent event) {
// updateUIFromSelection();
// }
// });
// // add an 'asset key' listener to the model. We used this to update the
// // key text in the assets tree. This is fired by the
// // AssetModel.setKey(key)
// _assetKeyListener = new PropertyChangeListener() {
//
// @Override
// public void propertyChange(PropertyChangeEvent evt) {
// _allAssetsViewer.refresh(evt.getSource());
// }
// };
// model.addPropertyChangeListener(AssetPackModel.PROP_ASSET_KEY,
//// _assetKeyListener);
// _allAssetsViewer.getTree().addDisposeListener(new DisposeListener() {
//
// @Override
// public void widgetDisposed(DisposeEvent e) {
// model.removePropertyChangeListener(AssetPackModel.PROP_ASSET_KEY,
//// _assetKeyListener);
// }
// });
// Transfer[] types = { LocalSelectionTransfer.getTransfer() };
// _allAssetsViewer.addDragSupport(DND.DROP_COPY | DND.DROP_MOVE, types, new
//// DragSourceAdapter() {
//
// @Override
// public void dragStart(DragSourceEvent event) {
// LocalSelectionTransfer.getTransfer().setSelection(_allAssetsViewer.getSelection());
// }
// });
//
// recoverEditingPoint();
//
// getEditorSite().setSelectionProvider(_allAssetsViewer);
//
// AssetPackUI.installAssetTooltips(_allAssetsViewer);
// }
//
// protected void moveAssetElement() {
// ListDialog dlg = new ListDialog(getSite().getShell());
// dlg.setContentProvider(new ArrayContentProvider());
// dlg.setLabelProvider(new AssetLabelProvider());
// AssetPackModel model = getModel();
// dlg.setInput(model.getSections());
// dlg.setTitle("Move Assets");
// dlg.setMessage("Move the selected assets to other section");
// dlg.setInitialSelections(new Object[] { model.getSections().get(0) });
// if (dlg.open() == Window.OK) {
// Object elemToMove = ((IStructuredSelection)
//// _allAssetsViewer.getSelection()).getFirstElement();
// AssetSectionModel moveToSection = (AssetSectionModel) dlg.getResult()[0];
// Object reveal = null;
// if (elemToMove instanceof AssetGroupModel) {
// AssetGroupModel groupToMove = (AssetGroupModel) elemToMove;
// if (moveToSection != groupToMove.getSection()) {
// List<AssetModel> assetsToMove = new ArrayList<>();
// for (AssetModel asset : groupToMove.getSection().getAssets()) {
// if (asset.getGroup() == groupToMove) {
// assetsToMove.add(asset);
// }
// }
// groupToMove.getSection().removeGroup(groupToMove);
// for (AssetModel asset : assetsToMove) {
// moveToSection.addAsset(asset, true);
// }
// }
// reveal = moveToSection.getGroup(groupToMove.getType());
// } else if (elemToMove instanceof AssetModel) {
// AssetModel asset = (AssetModel) elemToMove;
// if (asset.getSection() != moveToSection) {
// asset.getSection().removeAsset(asset);
// moveToSection.addAsset(asset, true);
// reveal = asset;
// }
// }
// if (reveal != null) {
// refresh();
// revealElement(reveal);
// }
// }
// }
//
// void removeAssetElement() {
// for (Object element : ((StructuredSelection)
//// _allAssetsViewer.getSelection()).toArray()) {
// if (element instanceof AssetSectionModel) {
// AssetSectionModel section = (AssetSectionModel) element;
// section.getPack().removeSection(section);
// } else if (element instanceof AssetGroupModel) {
// AssetGroupModel group = (AssetGroupModel) element;
// group.getSection().removeGroup(group);
// } else if (element instanceof AssetModel) {
// AssetModel asset = (AssetModel) element;
// asset.getGroup().remove(asset);
// }
// }
// refresh();
// _allAssetsViewer.setSelection(StructuredSelection.EMPTY);
// }
//
// protected void openNewSectionDialog() {
// AssetPackModel model = getModel();
// InputDialog dlg = new InputDialog(getSite().getShell(), "New Section", "Enter
//// the section key:",
// model.createKey("section"), new IInputValidator() {
//
// @Override
// public String isValid(String newText) {
// return model.hasKey(newText) ? "That key already exists, use other." : null;
// }
// });
// if (dlg.open() == Window.OK) {
// String result = dlg.getValue();
// AssetSectionModel section = new AssetSectionModel(result, model);
// model.addSection(section, true);
// refresh();
// revealElement(section);
// }
// }
//
// protected void openNewAssetDialog() {
// try {
//
// Object selection = ((StructuredSelection)
//// _allAssetsViewer.getSelection()).getFirstElement();
//
// AssetSectionModel section;
// AssetType initialType = null;
// if (selection instanceof AssetModel) {
// AssetModel asset = (AssetModel) selection;
// initialType = asset.getType();
// section = asset.getSection();
// } else if (selection instanceof AssetGroupModel) {
// AssetGroupModel group = (AssetGroupModel) selection;
// initialType = group.getType();
// section = group.getSection();
// } else {
// section = (AssetSectionModel) selection;
// }
//
// AssetFactory factory = null;
//
// if (initialType != null) {
// factory = AssetFactory.getFactory(initialType);
// }
//
// if (factory == null) {
// AddAssetToPackDialog dlg = new AddAssetToPackDialog(getSite().getShell());
//
// dlg.setInitialType(initialType);
//
// if (dlg.open() == Window.OK) {
// factory = dlg.getSelection();
// }
// }
//
// if (factory != null) {
// AssetType type = factory.getType();
// AssetPackModel pack = getModel();
// String key = pack.createKey(type.name());
// AssetModel asset = factory.createAsset(key, section);
// section.addAsset(asset, true);
// refresh();
// revealElement(asset);
// }
// } catch (Exception e) {
// e.printStackTrace();
// throw new RuntimeException(e);
// }
// }
//
// void updateUIFromSelection() {
// IStructuredSelection selection = (IStructuredSelection)
//// _allAssetsViewer.getSelection();
// boolean hasSelection = !selection.isEmpty();
// boolean hasOneElement = hasSelection && selection.toArray().length == 1;
// Object firstElement = selection.getFirstElement();
//
// AssetType initialType = null;
// if (firstElement instanceof AssetModel) {
// AssetModel asset = (AssetModel) firstElement;
// initialType = asset.getType();
// } else if (firstElement instanceof AssetGroupModel) {
// AssetGroupModel group = (AssetGroupModel) firstElement;
// initialType = group.getType();
// }
//
// if (initialType == null) {
// _addAssetButton.setText("Add Asset");
// } else {
// _addAssetButton.setText("+ " + initialType);
// }
//
// // buttons
//
// _addAssetButton.setEnabled(hasSelection);
// _removeButton.setEnabled(hasSelection);
// _previewButton.setEnabled(hasOneElement && firstElement instanceof
//// AssetModel);
//
// _moveButton.setEnabled(firstElement != null
// && (firstElement instanceof AssetModel || firstElement instanceof
//// AssetGroupModel));
//
// // editors
// updateEditor(hasOneElement ? firstElement : null);
// }
//
// private void updateEditor(Object element) {
// _editingElement = element;
// Composite editor = _emptyAssetEditorComp;
// if (element != null) {
// if (element instanceof AssetModel) {
// AssetModel asset = (AssetModel) element;
// AssetType assetType = asset.getType();
// switch (assetType) {
// case image:
// _imageAssetEditorComp.setModel((ImageAssetModel) asset);
// editor = _imageAssetEditorComp;
// break;
// case spritesheet:
// _spritesheetAssetEditorComp.setModel((SpritesheetAssetModel) asset);
// editor = _spritesheetAssetEditorComp;
// break;
// case audio:
// _audioAssetEditorComp.setModel((AudioAssetModel) asset);
// editor = _audioAssetEditorComp;
// break;
// case audiosprite:
// _audioSpriteEditorComp.setModel((AudioSpriteAssetModel) asset);
// editor = _audioSpriteEditorComp;
// break;
// case tilemap:
// _tilemapAssetEditorComp.setModel((TilemapAssetModel) asset);
// editor = _tilemapAssetEditorComp;
// break;
// case bitmapFont:
// _bitmapFontAssetEditorComp.setModel((BitmapFontAssetModel) asset);
// editor = _bitmapFontAssetEditorComp;
// break;
// case physics:
// _physicsAssetEditorComp.setModel((PhysicsAssetModel) asset);
// editor = _physicsAssetEditorComp;
// break;
// case atlas:
// _atlasAssetEditorComp.setModel((AtlasAssetModel) asset);
// editor = _atlasAssetEditorComp;
// break;
// case text:
// case json:
// _textAssetEditorComp.setModel((TextAssetModel) asset);
// editor = _textAssetEditorComp;
// break;
// case script:
// _scriptAssetEditorComp.setModel((ScriptAssetModel) asset);
// editor = _scriptAssetEditorComp;
// break;
// case binary:
// _binaryAssetEditorComp.setModel((BinaryAssetModel) asset);
// editor = _binaryAssetEditorComp;
// break;
// default:
// break;
// }
// } else if (element instanceof AssetSectionModel) {
// _sectionEditorComp.setModel((AssetSectionModel) element);
// editor = _sectionEditorComp;
// } else if (element instanceof AssetGroupModel) {
// _groupEditorComp.setModel((AssetGroupModel) element);
// editor = _groupEditorComp;
// }
// }
//
// StackLayout layout = (StackLayout) _editorsContainer.getLayout();
// layout.topControl = editor;
// _editorsContainer.layout();
// ScrolledForm form = getManagedForm().getForm();
// form.reflow(true);
// }
//
// public void refresh() {
// _allAssetsViewer.refresh();
// }
//
// @Override
// public AssetPackEditor getEditor() {
// return (AssetPackEditor) super.getEditor();
// }
//
// public AssetPackModel getModel() {
// return getEditor().getModel();
// }
//
// public Object getEditingElement() {
// return _editingElement;
// }
//
// private static final QualifiedName EDITING_NODE = new
//// QualifiedName("phasereditor.assetpack", "editingNode_v2");
// private Button _previewButton;
// private Label _label_1;
// private Label _label_2;
//
// public void saveEditingPoint() {
// AssetPackModel pack = getModel();
// if (pack != null) {
// Object elem = _editingElement;
// if (elem != null) {
// IFile file = pack.getFile();
// try {
// file.setPersistentProperty(EDITING_NODE, pack.getStringReference(elem));
// } catch (CoreException e) {
// e.printStackTrace();
// }
// }
// }
// }
//
// public void recoverEditingPoint() {
// AssetPackModel pack = getModel();
// IFile file = pack.getFile();
// try {
// String str = file.getPersistentProperty(EDITING_NODE);
// if (str != null) {
// Object elem = pack.getElementFromStringReference(str);
// if (elem != null) {
// if (elem instanceof AssetGroupModel) {
// revealElement(((AssetGroupModel) elem).getSection());
// } else if (elem instanceof AssetModel) {
// revealElement(((AssetModel) elem).getSection());
// revealElement(((AssetModel) elem).getGroup());
// }
// revealElement(elem);
// }
// }
// } catch (Exception e) {
// e.printStackTrace();
// }
//
// }
//
// public void revealElement(Object elem) {
// if (elem != null) {
// _allAssetsViewer.getTree().setFocus();
// _allAssetsViewer.setSelection(new StructuredSelection(elem), true);
// }
// }
//
// public ISelection getSelection() {
// return _allAssetsViewer.getSelection();
// }
// }
