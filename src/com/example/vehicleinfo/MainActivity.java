package com.example.vehicleinfo;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	Intent intent;
	Button btnCheckingCampaign, btnDrinkAndDrive, btnEmergencyExit,
			btnHarvesterRegistration, btnOutstandingRecovery,
			btnPollutionInvestigation, btnPucChecking, btnRedLightJumping,
			btnRentalListInfo, btnReplactorInfo,
			btnUseOfMobile,btnVehicleWithoutFitness,btnChooseCity;

	byte[] Key = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//This is how to use CryptLib.java

		   try {
		    	CryptLib _crypt = new CryptLib();
	            String plainText = "Transaction_ID=102|Reference_Numer=6766";
		    	
		    	 String cypherText = _crypt.encrypt(plainText); //encrypt
		    	
		    	 
		    	 Log.e("cypherText", cypherText);
		    	 
		    	String dectext = _crypt.decrypt(cypherText); //decrypt
		    	Log.e("dectext", dectext);
		    	
		    	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	//If you are getting "java.security.InvalidKeyException: Illegal key size" error while compiling, see this - http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters

		
		
		
		
		inIt();
	}
	
	private byte [] crypto(byte[] plaintext){
		byte[] sessionKey = null; //Where you get this from is beyond the scope of this post
		byte[] iv = null ; //Ditto
		byte[] ciphertext=null;
		//plaintext = null; //Whatever you want to encrypt/decrypt
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//You can use ENCRYPT_MODE or DECRYPT_MODE
		try {
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(sessionKey, "AES"), new IvParameterSpec(iv));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 ciphertext = cipher.doFinal(plaintext);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ciphertext;
	}
private void openBrowser(){
	
	 Uri uri = Uri.parse("http://www.google.com");
	 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
	 startActivity(intent);
	
}
	private void inIt() {
		btnRentalListInfo = (Button) findViewById(R.id.btn_rental_activity);
		btnDrinkAndDrive = (Button) findViewById(R.id.btn_drink_and_drive);
		btnEmergencyExit = (Button) findViewById(R.id.btn_emergency);
		btnHarvesterRegistration = (Button) findViewById(R.id.btn_harvester_reg);
		btnUseOfMobile = (Button) findViewById(R.id.btn_mobile_info);
		btnOutstandingRecovery = (Button) findViewById(R.id.btn_outstanding_info);
		btnPollutionInvestigation = (Button) findViewById(R.id.btn_pollution);
		btnPucChecking = (Button) findViewById(R.id.btn_puc_checking);
		btnRedLightJumping = (Button) findViewById(R.id.btn_red_light_info);
		btnReplactorInfo = (Button) findViewById(R.id.btn_replector);
		btnCheckingCampaign = (Button) findViewById(R.id.btn_campaign_info);
		btnVehicleWithoutFitness = (Button) findViewById(R.id.btn_without_fitness);
		btnChooseCity = (Button) findViewById(R.id.btn_choose_city);
		btnChooseCity.setOnClickListener(this);
		btnRentalListInfo.setOnClickListener(this);
		btnCheckingCampaign.setOnClickListener(this);
		btnDrinkAndDrive.setOnClickListener(this);
		btnEmergencyExit.setOnClickListener(this);
		btnHarvesterRegistration.setOnClickListener(this);
		btnUseOfMobile.setOnClickListener(this);
		btnOutstandingRecovery.setOnClickListener(this);
		btnPollutionInvestigation.setOnClickListener(this);
		btnPucChecking.setOnClickListener(this);
		btnRedLightJumping.setOnClickListener(this);
		btnReplactorInfo.setOnClickListener(this);
		btnCheckingCampaign.setOnClickListener(this);
		btnVehicleWithoutFitness.setOnClickListener(this);
		
		
   
	    }
	
					
	@Override
	public void onClick(View v) {

		int id = v.getId();
		switch (id) {
		case R.id.btn_rental_activity:

			intent = new Intent(MainActivity.this, RentalListInfoActivity.class);
			startActivity(intent);

			break;

		case R.id.btn_drink_and_drive:
			intent = new Intent(MainActivity.this, DrunkAndDriveActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_emergency:
			intent = new Intent(MainActivity.this, EmergencyExitActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_harvester_reg:
			intent = new Intent(MainActivity.this,
					HarvesterRegistrationActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_mobile_info:
			intent = new Intent(MainActivity.this, UseOfMobileActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_outstanding_info:
			intent = new Intent(MainActivity.this,
					OutstandingRecoveryInfoActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_pollution:
			intent = new Intent(MainActivity.this,
					PollutionInvestigationCentres.class);
			startActivity(intent);
			break;

		case R.id.btn_puc_checking:
			intent = new Intent(MainActivity.this, PUCCheckingActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_red_light_info:
			intent = new Intent(MainActivity.this,
					RedLightJumpingActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_replector:
			intent = new Intent(MainActivity.this, ReplactorInfoActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_without_fitness:
			intent = new Intent(MainActivity.this,
					VehiclesWithoutFitnessActivity.class);
			startActivity(intent);
			break;
			
		case R.id.btn_campaign_info:
			intent = new Intent(MainActivity.this,
					CheckingCampaignActivity.class);
			startActivity(intent);
			break;

		case R.id.btn_choose_city:
			//openBrowser();
			cityDialog();
			
			
			break;

		default:
			break;
		}
	}

	private void cityDialog(){
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
		builderSingle.setTitle(R.string.choose_city_name);
		
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
		        MainActivity.this,
		        android.R.layout.select_dialog_singlechoice);
		arrayAdapter.add(getResources().getString(R.string.anooppur));
		arrayAdapter.add(getResources().getString(R.string.aagar_malwa));
		arrayAdapter.add(getResources().getString(R.string.alirajpur));
		arrayAdapter.add(getResources().getString(R.string.ashoknagar));
		arrayAdapter.add(getResources().getString(R.string.badwani));
		arrayAdapter.add(getResources().getString(R.string.balaghat));
		arrayAdapter.add(getResources().getString(R.string.betul));
		arrayAdapter.add(getResources().getString(R.string.bhind));
		arrayAdapter.add(getResources().getString(R.string.bhopal));
		arrayAdapter.add(getResources().getString(R.string.badwani));
		arrayAdapter.add(getResources().getString(R.string.burhanpur));
		arrayAdapter.add(getResources().getString(R.string.chatarpur));
		arrayAdapter.add(getResources().getString(R.string.chindwada));
		arrayAdapter.add(getResources().getString(R.string.damoh));
		arrayAdapter.add(getResources().getString(R.string.datiya));
		arrayAdapter.add(getResources().getString(R.string.dewas));
		arrayAdapter.add(getResources().getString(R.string.dhar));
		arrayAdapter.add(getResources().getString(R.string.dindori));
		arrayAdapter.add(getResources().getString(R.string.duna));
		arrayAdapter.add(getResources().getString(R.string.badwani));
		arrayAdapter.add(getResources().getString(R.string.burhanpur));
		arrayAdapter.add(getResources().getString(R.string.chatarpur));
		arrayAdapter.add(getResources().getString(R.string.chindwada));
		arrayAdapter.add(getResources().getString(R.string.damoh));
		arrayAdapter.add(getResources().getString(R.string.datiya));
		arrayAdapter.add(getResources().getString(R.string.gwalior));
		arrayAdapter.add(getResources().getString(R.string.harda));
		arrayAdapter.add(getResources().getString(R.string.hosangabad));
		arrayAdapter.add(getResources().getString(R.string.indore));
		arrayAdapter.add(getResources().getString(R.string.jabulpur));
		arrayAdapter.add(getResources().getString(R.string.jhabua));
		arrayAdapter.add(getResources().getString(R.string.katni));
		arrayAdapter.add(getResources().getString(R.string.khadwa));
		arrayAdapter.add(getResources().getString(R.string.khargoun));
		arrayAdapter.add(getResources().getString(R.string.mandla));
		arrayAdapter.add(getResources().getString(R.string.mandsout));
		arrayAdapter.add(getResources().getString(R.string.murena));
		arrayAdapter.add(getResources().getString(R.string.narsingpur));
		arrayAdapter.add(getResources().getString(R.string.neemach));
		arrayAdapter.add(getResources().getString(R.string.panna));
		arrayAdapter.add(getResources().getString(R.string.rajgarh));
		arrayAdapter.add(getResources().getString(R.string.ratlam));
		arrayAdapter.add(getResources().getString(R.string.rewa));
		arrayAdapter.add(getResources().getString(R.string.satna));
		arrayAdapter.add(getResources().getString(R.string.cehore));
		arrayAdapter.add(getResources().getString(R.string.sivni));
		arrayAdapter.add(getResources().getString(R.string.sehdolw));
		arrayAdapter.add(getResources().getString(R.string.shajapur));
		arrayAdapter.add(getResources().getString(R.string.shyopur));
		arrayAdapter.add(getResources().getString(R.string.shivpurir));
		arrayAdapter.add(getResources().getString(R.string.sidhi));
		arrayAdapter.add(getResources().getString(R.string.singroli));
		arrayAdapter.add(getResources().getString(R.string.tikamgarh));
		arrayAdapter.add(getResources().getString(R.string.ujjain));
		arrayAdapter.add(getResources().getString(R.string.umariya));
		arrayAdapter.add(getResources().getString(R.string.vidisha));
		
		builderSingle.setNegativeButton(
		        "cancel",
		        new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		                dialog.dismiss();
		            }
		        });

		builderSingle.setAdapter(
		        arrayAdapter,
		        new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		                String strName = arrayAdapter.getItem(which);
		                btnChooseCity.setText(strName);
		                
		               
		            }
		        });
		builderSingle.show();
		
		
	}
	
}
