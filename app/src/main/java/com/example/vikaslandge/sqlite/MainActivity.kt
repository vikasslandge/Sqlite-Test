package com.example.vikaslandge.sqlite

import android.content.ContentValues
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dbase = openOrCreateDatabase("emp", Context.MODE_PRIVATE,null)
        dbase.execSQL("create table if not exists emp (_id integer primary key autoincrement , emp_id number, name varchar(20),desig varchar(20),dep varchar(20))")

        b1.setOnClickListener(){

            if (!et1.text.isEmpty()&&!et2.text.isEmpty() &&!et3.text.isEmpty()&&!et4.text.isEmpty()){
            var cv = ContentValues()
            cv.put("emp_id",et1.text.toString().toInt())
            cv.put("name",et2.text.toString())
            cv.put("desig",et3.text.toString())
            cv.put("dep",et4.text.toString())

            var status = dbase.insert("emp",null,cv)
                if (status!=-1.toLong()){
                    Toast.makeText(this,"Insertion is successful",Toast.LENGTH_LONG).show()
                    et1.setText("")
                    et2.setText("")
                    et3.setText("");et4.setText("")

                }else{
                    Toast.makeText(this,"Insertion failed",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"fill all details",Toast.LENGTH_LONG).show()
            }
            }

        b2.setOnClickListener(){

           var c = dbase.query( "emp", null,null,null,null,null,"emp_id asc",null)

          //  var c = dbase.query("emp",null,"emp_id=?", arrayOf(et1.text.toString()),null,null,null)
          //  var c = dbase.rawQuery("select * from emp where emp_id>0",null)
           // var c = dbase.query("emp",null,null,null,null,null,"name desc")
           // var c = dbase.query("emp",null,"name=?", arrayOf(et2.text.toString()),null,null,null)

            var from : Array<String> = arrayOf("emp_id","name","desig","dep")
            var to :  IntArray = intArrayOf(R.id.textview1,R.id.textView2,R.id.textView3,R.id.textView4)

            var myadpter = SimpleCursorAdapter(this,R.layout.indiview,c, from, to)
            lview.adapter = myadpter
        }

        b3.setOnClickListener(){
            if (!et1.text.isEmpty()&&!et2.text.isEmpty() &&!et3.text.isEmpty() ){
                var cv = ContentValues()
                cv.put("emp_id",et1.text.toString().toInt())
                cv.put("name",et2.text.toString())
                cv.put("desig",et3.text.toString())


                var status = dbase.update("emp",cv,"emp_id=?", arrayOf(et1.text.toString()))
                if (status!=-1){
                    Toast.makeText(this,"Data Updated successful",Toast.LENGTH_LONG).show()
                    et1.setText("")
                    et2.setText("")
                    et3.setText("");et4.setText("")
                    b2.performClick()

                }else{
                    Toast.makeText(this, "Data update failed",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"fill all details",Toast.LENGTH_LONG).show()
            }


        }

        b4.setOnClickListener(){
            if (!et1.text.isEmpty() ){
                var cv = ContentValues()
                cv.put("emp_id",et1.text.toString().toInt())
                cv.put("name",et2.text.toString())
                cv.put("desig",et3.text.toString())


                var status = dbase.delete("emp","emp_id=?", arrayOf(et1.text.toString()))
                if (status!=-1){
                    Toast.makeText(this,"Data deleted successful",Toast.LENGTH_LONG).show()
                    et1.setText("")
                    et2.setText("")
                    et3.setText("");et4.setText("")
                    b2.performClick()

                }else{
                    Toast.makeText(this, "Data deletion failed",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"provide emp id to delete its record",Toast.LENGTH_LONG).show()
            }


        }
    }
}
