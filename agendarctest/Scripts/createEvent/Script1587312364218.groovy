import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

WebUI.click(findTestObject('page_calendar/link_createEvent'))

WebUI.verifyTextPresent('Création', false)

WebUI.setText(findTestObject('page_create_event/input_name'), 'Examen')

WebUI.executeJavaScript('document.getElementById(\'dateStart\').value=\'2020-04-27\';', [])

WebUI.setText(findTestObject('page_create_event/input_start_time'), '10:00')

WebUI.setText(findTestObject('page_create_event/input_end_date'), '2020-04-27')

WebUI.setText(findTestObject('page_create_event/input_end_time'), '12:00')

WebUI.setText(findTestObject('page_create_event/input_description'), 'Examen de traitement d\'images')

WebUI.selectOptionByLabel(findTestObject('page_create_event/input_select_calendar'), 'monCalendrier', false)

WebUI.click(findTestObject('page_create_event/input_submit'))

