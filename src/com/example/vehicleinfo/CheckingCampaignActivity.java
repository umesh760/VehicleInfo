package com.example.vehicleinfo;

import java.util.Calendar;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class CheckingCampaignActivity extends Activity implements OnClickListener{
	final static int DIALOG_ID = 0;
	int year_x, month_x, day_x;
	Button btnDate;
	EditText edtStartDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checking_campaign);

		inIt();
	}
	
	private void inIt(){
		final Calendar calendar = Calendar.getInstance();
		year_x = calendar.get(Calendar.YEAR);
		month_x = calendar.get(Calendar.MONTH);
		day_x = calendar.get(Calendar.DAY_OF_MONTH);
		btnDate =(Button)findViewById(R.id.btn_date);
		btnDate.setOnClickListener(this);
		edtStartDate = (EditText) findViewById(R.id.edt_from_date);
		
	}
	public void showDateDialog() {

		showDialog(DIALOG_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		if (id == DIALOG_ID)
			return new DatePickerDialog(this, dplistner, year_x, month_x, day_x);
		return null;
	}

	public DatePickerDialog.OnDateSetListener dplistner = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			year_x = year;
			month_x = monthOfYear + 1;
			day_x = dayOfMonth;

			String selectedDate = day_x + "/" + month_x + "/" + year_x;
			edtStartDate.setText(selectedDate);
			
		}
	};

	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		switch (id) {
		case R.id.btn_date:
			
			showDateDialog();
			
			break;

		default:
			break;
		}
		
	}
}
