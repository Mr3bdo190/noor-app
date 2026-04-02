import 'package:flutter/material.dart';
import 'mushaf_image_screen.dart';

class KhatmahPlanScreen extends StatelessWidget {
  const KhatmahPlanScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(20),
      children: [
        const SizedBox(height: 40),
        const Text('خطة الختمة', style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold, color: Color(0xFF0F4C3A))),
        const Text('نظم وردك اليومي واختم القرآن بسهولة', style: TextStyle(color: Colors.grey, fontSize: 16)),
        const SizedBox(height: 30),
        
        // الكارت الرئيسي (مستوحى من ختمة)
        Container(
          padding: const EdgeInsets.all(20),
          decoration: BoxDecoration(
            gradient: const LinearGradient(colors: [Color(0xFF0F4C3A), Color(0xFF166850)]),
            borderRadius: BorderRadius.circular(20),
            boxShadow: [BoxShadow(color: Colors.black12, blurRadius: 10, spreadRadius: 2)],
          ),
          child: Column(
            children: [
              const Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text('الختمة الحالية', style: TextStyle(color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold)),
                  Icon(Icons.menu_book, color: Color(0xFFD4AF37)), // لون ذهبي
                ],
              ),
              const SizedBox(height: 20),
              Stack(
                alignment: Alignment.center,
                children: [
                  SizedBox(
                    width: 100, height: 100,
                    child: CircularProgressIndicator(value: 0.15, backgroundColor: Colors.white.withOpacity(0.2), color: const Color(0xFFD4AF37), strokeWidth: 8),
                  ),
                  const Text('15%', style: TextStyle(color: Colors.white, fontSize: 22, fontWeight: FontWeight.bold)),
                ],
              ),
              const SizedBox(height: 20),
              const Text('ورد اليوم: سورة البقرة (صفحة 22 إلى 41)', style: TextStyle(color: Colors.white, fontSize: 16)),
              const SizedBox(height: 15),
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: const Color(0xFFD4AF37),
                  shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
                  minimumSize: const Size(double.infinity, 50)
                ),
                onPressed: () {
                  Navigator.push(context, MaterialPageRoute(builder: (context) => const MushafImageScreen(startPage: 22)));
                },
                child: const Text('اقرأ ورد اليوم', style: TextStyle(color: Color(0xFF0F4C3A), fontSize: 18, fontWeight: FontWeight.bold)),
              )
            ],
          ),
        ),
      ],
    );
  }
}
