# MyToolbarTemplate 
SDK API23になったのでAndroidアプリの雛形をAppCompatActivityで書いてみました。AndroidBootStrapつきです。
ToolBar,NavigaterDrawer,SerchView,menuに一般的なダイアログとListViewのテンプレートをつけました。  
Clone後適当に改造してね
##Usage
*NavigaterDrawerに項目を追加するにはMyConst.java内DRAWER_ITEMS定数に以下のようにDraweItemインスタンスを作ってね。  
new DrawerItem("Bootstrapのアイコン名", "navigationDrawrに表示する表題")  
*ダイアログフラクメントはOKのみ(OkDialog,java)、OK,Cancelボタン(OkCancelDialog.java)、
テキスト入力つき(EditTextDialog.java)の３種類です。呼び出し方はMyDialogShowFragment.javaを参照のこと
## Anithing Else
AndroidStudio 1.2
Genymotion API22,API18で確認
