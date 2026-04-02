import 'package:flutter/material.dart';

class QuranViewScreen extends StatelessWidget {
  final String surahName;
  const QuranViewScreen({super.key, required this.surahName});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(surahName), backgroundColor: const Color(0xFF0F172A)),
      body: Container(
        padding: const EdgeInsets.all(20),
        color: const Color(0xFF0F172A),
        child: SingleChildScrollView(
          child: Text(
            "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ\n\nالْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ (1) الرَّحْمَنِ الرَّحِيمِ (2) مَالِكِ يَوْمِ الدِّينِ (3) إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ (4) اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ (5) صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ (6)",
            style: const TextStyle(fontSize: 24, color: Colors.white, height: 2, fontFamily: 'Traditional Arabic'),
            textAlign: TextAlign.center,
          ),
        ),
      ),
    );
  }
}
