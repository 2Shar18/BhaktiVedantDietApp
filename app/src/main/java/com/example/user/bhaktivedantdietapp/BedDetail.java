package com.example.user.bhaktivedantdietapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.Calendar;

import static com.example.user.bhaktivedantdietapp.ConnectionClass.CONN;
import static java.lang.String.valueOf;

public class BedDetail extends AppCompatActivity {

    TextView bedNo, patName, patGender, patAge, diag, diet, tvDocName, tvUpdated;
    EditText etDietRemark;
    CheckBox cbDD, cbRD, cbBLAND, cbCD, cbFF, cbHPD, cbLPD, cbSF, cbSRD;
    Spinner spinner;

    String bed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_detail);
        Bundle bundle = getIntent().getExtras();

//        floor = bundle.getString("floor");
//        ward = bundle.getString("ward");
//        clas = bundle.getString("class");
        bed = bundle.getString("bed");

        spinner = (Spinner) findViewById(R.id.spBasicDiet);
        bedNo = (TextView) findViewById(R.id.BedNo);
        patName = (TextView) findViewById(R.id.PatientName);
        patGender = (TextView) findViewById(R.id.Gender);
        patAge = (TextView) findViewById(R.id.Age);
        diag = (EditText) findViewById(R.id.etProvDiag);
        diet = (TextView) findViewById(R.id.BasicDiet);
        etDietRemark = (EditText) findViewById(R.id.etDietRemark);
        cbDD = (CheckBox) findViewById(R.id.cbDD);
        cbRD = (CheckBox) findViewById(R.id.cbRD);
        cbBLAND = (CheckBox) findViewById(R.id.cbBLAND);
        cbCD = (CheckBox) findViewById(R.id.cbCD);
        cbFF = (CheckBox) findViewById(R.id.cbFF);
        cbHPD = (CheckBox) findViewById(R.id.cbHPD);
        cbLPD = (CheckBox) findViewById(R.id.cbLPD);
        cbSF = (CheckBox) findViewById(R.id.cbSF);
        cbSRD = (CheckBox) findViewById(R.id.cbSRD);
        tvDocName = (TextView) findViewById(R.id.tvDocName);
        tvUpdated = (TextView) findViewById(R.id.tvUpdated);

        bedNo.setText(bedNo.getText() +" "+ bed);
        String name="", gender="", ipc="", bd="", pd="", DietRemark="", DD = "0", SRD = "0", dn="",
                SaltFree = "0", RD = "0", HPD= "0", LPD="0", BLAND = "0", FatFree="0", ColdDiet = "0";
//        Date dt = new Date(Calendar.getInstance().getTime().getTime());
        Date dt = null;
        Time dtt = null;
        String dt1 = "";

        int age=0;
        try {

            Connection DbConn = CONN();

            Log.w("Connection","open");
            Statement stmt = DbConn.createStatement();

            String ipSelectQuery = "select * from vw_BedPersonDetail where BedCode='"+bed+"'";
            ResultSet rs = stmt.executeQuery(ipSelectQuery);
            rs.next();
            name = rs.getString("PatientName");
            gender = rs.getString("PatientGender");
            age = rs.getInt("PatientAge");
            ipc = rs.getString("PatientIpCaseNumber");

            String dietQ = "select * from VW_Diet_Status where PatientIPCaseNumber = '"+ipc+"' and isActive = 1";
            ResultSet rs1 = stmt.executeQuery(dietQ);
            rs1.next();

            bd = rs1.getString("BasicDiet");
            pd = rs1.getString("ProvisionalDiagnosis");
            DietRemark = rs1.getString("DietRemark");
            DD = rs1.getString("DD");
            SRD = rs1.getString("SRD");
            SaltFree = rs1.getString("SaltFree");
            RD = rs1.getString("RD");
            HPD= rs1.getString("HPD");
            LPD= rs1.getString("LPD");
            BLAND = rs1.getString("BLAND");
            FatFree=rs1.getString("FatFree");
            ColdDiet = rs1.getString("ColdDiet");

            String dietQ1 = "select * from DietTable where PatientIPCaseNumber = '"+ipc+"' and isActive = 1";
            ResultSet rs2 = stmt.executeQuery(dietQ1);
            rs2.next();
            dn = rs2.getString("UserName");
            dt1 = rs2.getString("RqstDate");

            cbDD.setChecked(DD.equals("1"));
            cbSRD.setChecked(SRD.equals("1"));
            cbSF.setChecked(SaltFree.equals("1"));
            cbRD.setChecked(RD.equals("1"));
            cbHPD.setChecked(HPD.equals("1"));
            cbLPD.setChecked(LPD.equals("1"));
            cbBLAND.setChecked(BLAND.equals("1"));
            cbFF.setChecked(FatFree.equals("1"));
            cbCD.setChecked(ColdDiet.equals("1"));
            String[] some_array = getResources().getStringArray(R.array.basic_diet_arrays);
            for (int i = 0; i < some_array.length; i++){
                if (some_array[i].equals(bd)){
                    spinner.setSelection(i);
                    break;
                }
            }

            DbConn.close();

        } catch (Exception e)
        {
            Log.w("Error connection","" + e.getMessage());
        }
        patName.setText(patName.getText()+" "+name);
        patGender.setText(patGender.getText()+" "+gender);
        patAge.setText(patAge.getText()+" "+age);
//        diet.setText(diet.getText()+" "+bd);
        tvDocName.setText(tvDocName.getText() + " " + dn);
//        if (dt == null)
//            tvUpdated.setText(" ");
//        else
            tvUpdated.setText(tvUpdated.getText() + " " + dt1);
        diag.setText(pd);
        etDietRemark.setText(DietRemark);;

    }

    public void onBack(View view){
        finish();
    }
    
    public void onUpdate(View view){

        String bd="", pd="", DietRemark="", DD = "0", SRD = "0", id="",
                SaltFree = "0", RD = "0", HPD= "0", LPD="0", BLAND = "0", FatFree="0", ColdDiet = "0";

        pd = diag.getText().toString();
        DietRemark = etDietRemark.getText().toString();
        DD = cbDD.isChecked() ? "1" : "0";
        SRD = cbSRD.isChecked() ? "1" : "0";
        SaltFree = cbSF.isChecked() ? "1" : "0";
        RD = cbRD.isChecked() ? "1" : "0";
        HPD = cbHPD.isChecked() ? "1" : "0";
        LPD = cbLPD.isChecked() ? "1" : "0";
        BLAND = cbBLAND.isChecked() ? "1" : "0";
        FatFree = cbFF.isChecked() ? "1" : "0";
        ColdDiet = cbCD.isChecked() ? "1" : "0";
        bd = String.valueOf(spinner.getSelectedItem());

        try {

            Connection DbConn = CONN();

            Log.w("Connection","open");
            Statement stmt = DbConn.createStatement();

            String idSelectQuery = "select top 1 * from DietTable order by 1 desc";
            ResultSet rs = stmt.executeQuery(idSelectQuery);

            rs.next();
            id = rs.getString("DietRqstNumber");
            String partialID = id.substring(5);
            int numericID = Integer.parseInt(partialID);
            numericID ++;
            id = "BHDIE" + numericID;

            String xml = "<NewDataSet> " +
                    "<ServiceRqstHeader>" +
                    "<DietDetail>" +
                    "<ServiceRqstNumber>"+id+"</ServiceRqstNumber>" +
                    "<DietDetailRowID>1</DietDetailRowID>" +
                    "<IPCASEDETAILS>" +
                    "<ServiceRqstNumber>"+id+"</ServiceRqstNumber>" +
                    "<DietDetailRowID>1</DietDetailRowID>" +
                    "<IPCASEDETAILSRowID>1</IPCASEDETAILSRowID>" +
                    "<GBIPCaseDetail>" +
                    "<ServiceRqstNumber>"+id+"</ServiceRqstNumber>" +
                    "<DietDetailRowID>1</DietDetailRowID>" +
                    "<IPCASEDETAILSRowID>1</IPCASEDETAILSRowID>" +
                    "<GBIPCaseDetailRowID>1</GBIPCaseDetailRowID>" +
                    "<Textbox2>"+DietRemark+"</Textbox2>" +
                    "<Textbox1>"+pd+"</Textbox1>" +
                    "<cmbbasicdiet>"+bd+"</cmbbasicdiet>" +
                    "<ChkSDDD>"+DD+"</ChkSDDD>" +
                    "<ChkSDSRD>"+SRD+"</ChkSDSRD>" +
                    "<ChkSDSaltFree>"+SaltFree+"</ChkSDSaltFree>" +
                    "<ChkSDRD>"+RD+"</ChkSDRD>" +
                    "<ChkSDHPD>"+HPD+"</ChkSDHPD>" +
                    "<ChkSDLPD>"+LPD+"</ChkSDLPD>" +
                    "<ChkSDBLAND>"+BLAND+"</ChkSDBLAND>" +
                    "<ChkSDFatFree>"+FatFree+"</ChkSDFatFree>" +
                    "<ChkSDColdDiet>"+ColdDiet+"</ChkSDColdDiet>" +
                    "<TextBreakfast />" +
                    "<TextLunch />" +
                    "<TextBedTime>M</TextBedTime>" +
                    "<TextRemark />" +
                    "<TextE>Y</TextE>" +
                    "<TextM>Y</TextM>" +
                    "</GBIPCaseDetail>" +
                    "</IPCASEDETAILS>" +
                    "</DietDetail>" +
                    "</ServiceRqstHeader>" +
                    "</NewDataSet>";


            java.sql.Time time = new java.sql.Time(Calendar.getInstance().getTime().getTime());
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String dt = date + " " + time;

            String ipSelectQuery = "select * from vw_BedPersonDetail where BedCode='"+bed+"'";
            rs = stmt.executeQuery(ipSelectQuery);
            rs.next();
            String name = rs.getString("PatientName");
            String gender = rs.getString("PatientGender");
            int age = rs.getInt("PatientAge");
            String ipc = rs.getString("PatientIpCaseNumber");
            Log.i("Check","ipSelect");

            String pcSelectQuery = "select * from Vw_PatientIPCaseHeader where PatientIPCaseNumber = '"+ipc+"'";
            rs = stmt.executeQuery(pcSelectQuery);
            rs.next();
            String pCode = rs.getString("PatientCode");
            Log.i("Check","pcSelect");

            SharedPreferences pref = getApplicationContext().getSharedPreferences("DietLogin", 0);
            String user = pref.getString("user",null);

            if (user == null){
                finish();
            }

            String drSelectQuery = "select * from Vw_Personheader where PersonCode = '"+user+"'";
            rs = stmt.executeQuery(drSelectQuery);
            rs.next();
            String drName = rs.getString("PersonFullName");
            Log.i("Check","drSelect");

            String dietInactive = "UPDATE DietTable set isActive = 0 where PatientIPCaseNumber = '"+ipc+"'";
            stmt.executeUpdate(dietInactive);
            Log.i("Check","dietInactive");

            String dietInsert = "INSERT INTO DietTable VALUES('"+id+"','"+dt+"','"+ipc+"','"+pCode+"','"+user+"','"+drName+"','"+bed+"','"+xml+"',1)";
            stmt.execute(dietInsert);
            Log.i("Check","dietInsert");
            this.finish();

        }catch (Exception e)
        {
            Log.w("Error connection","" + e.getMessage());
        }
    }
}
