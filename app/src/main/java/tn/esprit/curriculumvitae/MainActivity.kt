package tn.esprit.curriculumvitae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.util.Patterns.EMAIL_ADDRESS

class MainActivity : AppCompatActivity() {
    private lateinit var picp : ImageView
    private lateinit var name : EditText;
    private lateinit var age : EditText;
    private lateinit var email : EditText
    private lateinit var gender : RadioGroup;
    private lateinit var male : RadioButton;
    private lateinit var female : RadioButton
    private lateinit var android : SeekBar;
    private lateinit var ios : SeekBar;
    private lateinit var flutter : SeekBar
    private lateinit var nextBtn : Button;
    private lateinit var resetBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        picp=findViewById(R.id.pp)
        name=findViewById(R.id.namee);
        age=findViewById(R.id.age);
        email=findViewById(R.id.email)
        gender=findViewById(R.id.gender);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female)
        android=findViewById(R.id.androidd);
        ios=findViewById(R.id.ioss);
        flutter=findViewById(R.id.flutterr)
        nextBtn=findViewById(R.id.btnnext);
        resetBtn=findViewById(R.id.btnreset)

        //if next is clicked
        nextBtn.setOnClickListener{
            if(validateInput()){
                evaluateSkills()
            }
        }

        //if RESET is clicked
        resetBtn.setOnClickListener{
            resetAll()
        }
    }

    private fun resetAll() {
        name.setText("")
        age.setText("")
        email.setText("")
        gender.check(R.id.male)
        android.progress = 0
        ios.progress = 0
        flutter.progress = 0
    }

    private fun evaluateSkills() {
        //Si tous les skills sont supérieurs à 80, on affiche « Vous êtes d'excellents skills!»
        if(android.progress>80 && ios.progress>80 && flutter.progress>80){
            Toast. makeText(this,getString(R.string.excellent_skills),Toast. LENGTH_SHORT).show()
        }
        //S’il a un skill qui est qui est supérieur à 80 (=la plus haute compétence), on affiche « Vous êtes excellent en NOM_SKILL! »
        else if(android.progress>80 || ios.progress>80 || flutter.progress>80){
            val map = mapOf("Android" to android.progress,"iOS" to ios.progress,"Flutter" to flutter.progress)
            Toast. makeText(this,getString(R.string.excellent_skill, bestSkill(map)),Toast. LENGTH_SHORT).show()
        }
        //Si tous les Skills sont <= 30, on affiche « Vous devez travailler vos skills! »
        else if(android.progress<=30 && ios.progress<=30 && flutter.progress<=30){
            Toast. makeText(this,getString(R.string.below_average_skills),Toast. LENGTH_SHORT).show()
        }
        //Sinon on affiche un message « Vous avez de bons skills!»
        else{
            Toast. makeText(this,getString(R.string.good_skills),Toast. LENGTH_SHORT).show()
        }
    }

    private fun validateInput(): Boolean {
        if(name.text.isEmpty() || age.text.isEmpty() || email.text.isEmpty()){
            Toast. makeText(this,getString(R.string.empty_fields),Toast. LENGTH_SHORT).show()
            return false;
        }

        else if(!EMAIL_ADDRESS.matcher(email.text).matches()){
            Toast. makeText(this,getString(R.string.check_email),Toast. LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun bestSkill(map:Map<String,Int>):String{
        var bestSkillKey=""
        var bestSkillVal=0
        for ((key,value) in map){
            if(value>bestSkillVal){
                bestSkillVal=value
                bestSkillKey=key
            }
        }
        return bestSkillKey
    }
    }

