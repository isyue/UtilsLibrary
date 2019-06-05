package xmqian.myutils.simple

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import com.coszero.utilslibrary.base.BaseActivity
import com.coszero.utilslibrary.utils.ToastUtils
import xmqian.myutils.R
import xmqian.myutils.simple.dummy.DummyContent

class FragmentActivity : BaseActivity(), ItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        ToastUtils.showLongMsg("测试一波加载")
    }

    override fun setWantShowContentView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_fragment)
    }

    override fun initView() {
        val frameLayout = findView<FrameLayout>(R.id.frame_layout) as FrameLayout
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ItemFragment()).commit();
        val mtoolBar = findViewById<Toolbar>(R.id.tool_bar)
        mtoolBar.setTitle("旁边")
        mtoolBar.setNavigationIcon(android.R.drawable.ic_menu_camera)
        mtoolBar.showOverflowMenu()
        setSupportActionBar(mtoolBar)
        mtoolBar.setNavigationOnClickListener { ToastUtils.showMsg(this, "返回值") }
        supportActionBar?.title = "使用后再测试"
    }

}