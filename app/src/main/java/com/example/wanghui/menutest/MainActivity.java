package com.example.wanghui.menutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //context_menu(TextView):演示ContextMenu
        TextView context_menu = findViewById(R.id.context_menu);
        //1.注册
     //   registerForContextMenu(context_menu);

        //重点 ：为按钮设置上下文操作模式【在应用顶部出现操作栏】
        //第一步：实现ActionMode 中 CallBack回调接口
        //第二步：在view 的长按事件中去启动上下文操作模式
        context_menu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActionMode(cb);
                return false;
            }
        });

        //popup_menu(TextView):演示PopupMenu
        final TextView popup_menu = findViewById(R.id.popup_menu);
        popup_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //①实例化PopupMenu对象[参数1：上下文对象；参数2：所依附的对象（view）]
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,popup_menu);
                //②加载菜单资源：利用MenuInflater将Menu资源加载到PopupMenu.getMenu()所返回的Menu对象中
                //popupMenu.getMenuInflater()得到弹出式菜单的MenuInflater对象，再去指定对应的资源
                 //参数1：资源Id;参数2：PopupMenu.getMenu()返回的对象
                //将R.menu.popup对应的菜单资源加载到 弹出式菜单（popupMenu.getMenu()）中
                popupMenu.getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                //③为PopupMenu设置点击监听器
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.rename:
                                Toast.makeText(MainActivity.this,"重命名",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete:
                                Toast.makeText(MainActivity.this,"删除",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                //④千万别忘记!!
                 popupMenu.show();
            }
        });
    }

   //第一步：实现ActionMode 中 CallBack回调接口
    ActionMode.Callback cb = new ActionMode.Callback() {
        //创建方法，在启动上下文操作模式startActionMode(Callback)时调用
       //在此配置上下文菜单的资源
       @Override
       public boolean onCreateActionMode(ActionMode mode, Menu menu) {
           Log.e("MainActivity:","创建");
           getMenuInflater().inflate(R.menu.context,menu);
           return true;
       }

       //在创建方法后进行调用
       @Override
       public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
           Log.e("MainActivity:","准备");
           return false;
       }

       //菜单项被点击，类似onContextItemSelected()方法
       @Override
       public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
           Log.e("MainActivity:","点击");
           switch (item.getItemId()){
               case R.id.copy:
                   Toast.makeText(MainActivity.this,"复制",Toast.LENGTH_SHORT).show();
                   break;
               case R.id.paste:
                   Toast.makeText(MainActivity.this,"粘贴",Toast.LENGTH_SHORT).show();
                   break;
               default:
                   break;
           }
           return true;
       }

       //上下文操作模式结束时被调用
       @Override
       public void onDestroyActionMode(ActionMode mode) {
           Log.e("MainActivity:","结束");

       }
   };
/**
    //2.创建onCreateContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //加载菜单资源<将菜单文件指定为刚刚的xml文件>
        //参数1：menu资源，传资源索引；参数2：菜单对象
        getMenuInflater().inflate(R.menu.context,menu);
    }
    //3.菜单项的操作
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.copy:
                Toast.makeText(MainActivity.this,"复制",Toast.LENGTH_SHORT).show();
                break;
            case R.id.paste:
                Toast.makeText(MainActivity.this,"粘贴",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
*/
    //创建OptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单资源<将菜单文件指定为刚刚的xml文件>
        //参数1：menu资源，传资源索引；参数2：菜单对象
        getMenuInflater().inflate(R.menu.option,menu);
        return true;
    }

    //OptionMenu菜单项的选中方法
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.save:
                Toast.makeText(MainActivity.this,"保存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(MainActivity.this,"设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
